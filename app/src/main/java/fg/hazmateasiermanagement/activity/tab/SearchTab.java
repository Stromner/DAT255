package fg.hazmateasiermanagement.activity.tab;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import fg.hazmateasiermanagement.R;
import fg.hazmateasiermanagement.database.AccessDatabase;
import fg.hazmateasiermanagement.database.Database;
import fg.hazmateasiermanagement.database.Element;

/**
 * The search tab, enables you to search or filter through the entire list of UN items and add them to your current route tab.
 *
 * @author  Wijk, Benjamin
 * @version 2014-10-17
 */
public class SearchTab extends Activity {
    Database db;
    AccessDatabase accessDatabase;
    EditText searchBar;
    LinearLayout searchListContainer;
    List<Element> elementList;
    List<Element> addedElements;
    TreeMap<Integer, String> searchMapDisplay;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchListContainer = (LinearLayout) findViewById(R.id.search_list);
        searchBar = (EditText) findViewById(R.id.search_text);

        accessDatabase = (AccessDatabase) getIntent().getSerializableExtra("db");

        searchMapDisplay = new TreeMap<Integer, String>(Collections.reverseOrder());
        db = new Database(this.getApplicationContext());
        //accessDatabase = new AccessDatabase(db);
        elementList = accessDatabase.getCompleteDatabase();
        addedElements = (LinkedList) getIntent().getSerializableExtra("addedElements");
        setupSearch();

    }

    /**
     * Initializes searchBar and its listener
     */
    private void setupSearch(){
        //Calls updateDisplay() whenever searchBar is changed and matches which items should be displayed.
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                searchMapDisplay.clear();


                //Test if string matches a UN-number (UN doesn't exceed 4 digits)
                if(s.toString().length() <= 4) {
                    try {
                       int uN = Integer.parseInt(s.toString());
                       Element temp;
                       temp = accessDatabase.getElement(uN);
                       if(temp != null){
                            String name = accessDatabase.getElement(uN).getName();
                            searchMapDisplay.put(uN, name);
                       }
                       updateDisplay();

                    }
                    catch (NumberFormatException e) {
                        search(s);
                        updateDisplay();
                    }
                }
                else{
                search(s);
                updateDisplay();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        //Forces afterTextchanged() to activate on app start.
        searchBar.setText("");
    }

    private void search (Editable s){
        String search = ".*" + s.toString().toLowerCase() + ".*";
        for (Element element: elementList) {
            if (s.toString().isEmpty() || element.getName().toLowerCase().matches(search)) {
                searchMapDisplay.put(element.getUNNumber(), element.getName());
                }
            }
    }

    /**
     * Updates the search_list view
     *
     */
    private void updateDisplay(){
        searchListContainer.removeAllViews();
        for(Map.Entry<Integer, String> entry: searchMapDisplay.entrySet()) {
            addListDisplayItem(entry.getValue(), entry.getKey());
        }
    }

    /**
     * Adds the item with various info to the search Display.
     * @param itemName name of item
     * @param uN UN-number for item
     */
    private void addListDisplayItem(String itemName, int uN){
        TextView displayItemText;
        final TextView displayUNText;
        final Button displayButton;

        LinearLayout displayItem = (LinearLayout) getLayoutInflater().inflate(R.layout.search_panel,null);
        displayItemText = (TextView) displayItem.findViewById(R.id.search_item_name);
        displayUNText = (TextView) displayItem.findViewById(R.id.search_item_UN);
        displayButton = (Button) displayItem.findViewById(R.id.button_add_search_item);

        displayItemText.setText(itemName);
        displayUNText.setText("UN: " + uN);
        for(Element element: addedElements)
            if(element.getUNNumber() == uN)
                displayButton.setText("Remove Item");
            else
                displayButton.setText("Add Item");

        displayButton.setOnClickListener(new CustomButtonOnClickListener(uN, displayButton) {
            @Override
            public void onClick(View v) {
                if(added == true){
                    addedElements.remove(accessDatabase.getElement(uN));
                    displayButton.setText("Add Item");
                    added = false;
                }
                else{
                    addedElements.add(accessDatabase.getElement(uN));
                    displayButton.setText("Remove Item");
                    added = true;
                }
            }
        });

        searchListContainer.addView(displayItem,0);
    }

}
