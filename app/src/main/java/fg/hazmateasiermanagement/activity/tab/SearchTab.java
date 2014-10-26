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
import android.widget.Toast;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import fg.hazmateasiermanagement.R;
import fg.hazmateasiermanagement.activity.MainActivity;
import fg.hazmateasiermanagement.database.AccessDatabase;
import fg.hazmateasiermanagement.database.Element;

/**
 * The search tab, enables you to search or filter through the entire list of UN items and add them to your current route tab.
 *
 * @author  Wijk, Benjamin
 * @version 2014-10-21
 */
public class SearchTab extends Activity {
    AccessDatabase accessDatabase;
    EditText searchBar;
    LinearLayout searchListContainer;
    List<Element> elementList;
    List<Element> addedElements;
    TreeMap<Integer, String> searchMapDisplay;
    CurrentTab currentTab;
    MainActivity mainActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchListContainer = (LinearLayout) findViewById(R.id.search_list);
        searchBar = (EditText) findViewById(R.id.search_text);

        searchMapDisplay = new TreeMap<Integer, String>(Collections.reverseOrder());
        addedElements = new LinkedList<Element>();

        accessDatabase = (AccessDatabase) getIntent().getSerializableExtra("db");
        elementList = accessDatabase.getCompleteDatabase();
        mainActivity = (MainActivity) getParent();
        currentTab = (CurrentTab) mainActivity.getCurrentTab();
        setupSearch();
    }

    /**
     * Initializes searchBar and its listener that runs every time the searchBar text changes.
     */
    private void setupSearch(){
        //Calls updateDisplay() whenever searchBar is changed and matches which items should be displayed.
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                searchMapDisplay.clear();

                //Test if string matches a UN-number (UN doesn't exceed 4 digits), otherwise search through list.
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

        //Forces afterTextChanged() to activate on app start.
        searchBar.setText("");
    }

    /**
     * Searches through existing elements and puts matching elements in the map that will be displayed.
     * @param s text being searched.
     */
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
     */
    private void updateDisplay(){
        searchListContainer.removeAllViews();
        for(Map.Entry<Integer, String> entry: searchMapDisplay.entrySet()) {
            addListDisplayItem(entry.getValue(), entry.getKey());
        }
    }

    /**
     * Adds matched items to the search view.
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

        getIntent().putExtra("uN", uN);

        displayButton.setOnClickListener(new View.OnClickListener() {
            private int uN = getIntent().getIntExtra("uN", 0);

            @Override
            public void onClick(View v) {
                if(currentTab.addElementPanel(accessDatabase.getElement(uN)))
                    Toast.makeText(getApplicationContext(), "UN " + uN +" has been added.",
                            Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "UN " + uN +" already exists.",
                            Toast.LENGTH_SHORT).show();

            }
        });

        searchListContainer.addView(displayItem,0);
    }

}
