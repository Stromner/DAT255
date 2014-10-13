package fg.hazmateasiermanagement;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import fg.hazmateasiermanagement.database.AccessDatabase;
import fg.hazmateasiermanagement.database.Database;

/**
 * Created by Benjamin on 2014-10-01.
 * The search tab, enables you to search or filter through the entire list of UN items and add them to your current route tab.
 *
 * NOTE: THIS CLASS CURRENTLY CREATES A DATABASE, THIS SHOULD BE CHANGED ASAP.
 */
public class SearchTab extends Activity {

    EditText searchBar;
    LinearLayout searchListContainer;
   // ArrayList<String> searchList;
    List<Element> elementList;
    TreeMap<Integer, String> searchMapDisplay;
    TreeMap<Integer, String> elementsMap;

  //  AccessDatabase database;
   // Database db;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchListContainer = (LinearLayout) findViewById(R.id.search_list);
        searchBar = (EditText) findViewById(R.id.search_text);

        searchMapDisplay = new TreeMap<Integer, String>();
        elementsMap = new TreeMap<Integer, String>();
        //searchList = new ArrayList<String>();

        //Extracting relevant information from the database to filter

       // elementList = database.getCompleteDatabase();
       // for(Element element: elementList)
         //   elementsMap.put(element.getUNNumber(), element.getLabel());
        addUNItem(1,"abob");
        addUNItem(2,"bobba");
        addUNItem(3,"abo");
        addUNItem(4,"aka");
        addUNItem(5,"akall");
        addUNItem(6,"akal");
        addUNItem(7,"amarta1");
        addUNItem(8,"amissile");
        addUNItem(9,"abomb");
        addUNItem(10,"ahej");
        addUNItem(11,"apa");
        addUNItem(12,"adig");
        addUNItem(13,"aquick");
        addUNItem(14,"acat");
        addUNItem(15,"araven");
        addUNItem(16,"asled");
        addUNItem(17,"mia");
        addUNItem(57,"kia");
       // for(int i=58; i<3000; i++)
         //   addUNItem(i,"a");

        //EXAMPLES
     //   database = new AccessDatabase(db);

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
                       int UN = Integer.parseInt(s.toString());
                       searchMapDisplay.put(UN, elementsMap.get(UN));
                       updateDisplay();
                    } catch (NumberFormatException e) {
                        search(s);
                        updateDisplay();
                    }
                }
                else{
                search(s);
                updateDisplay();
                }
            }

          /*  @Override
            public void afterTextChanged(Editable s) {
                searchMapDisplay.clear();
                if (! s.toString().isEmpty() ){
                    String search = ".*" + s.toString().toLowerCase() + ".*";
                    for (String temp : searchList) {
                        if (temp.toLowerCase().matches(search)) {
                            searchMapDisplay.add(temp);
                        }
                    }

                }
                updateDisplay();
            }*/
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
    }

    private void search (Editable s){
        if (! s.toString().isEmpty() ){
            String search = ".*" + s.toString().toLowerCase() + ".*";
            for (Map.Entry<Integer, String> entry : elementsMap.entrySet()) {
                if (entry.getValue().toLowerCase().matches(search)) {
                    searchMapDisplay.put(entry.getKey(), entry.getValue());
                }
            }
        }
    }

    /**
     * (Pointless) test function that will be removed.
     */
    private void addUNItem(int UN, String itemName){
        elementsMap.put(UN, itemName);
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
     * @param UN UN-number for item
     */
    private void addListDisplayItem(String itemName, int UN){
        TextView displayItemText;
        TextView displayUNText;
        TextView displayButton;

        LinearLayout displayItem = (LinearLayout) getLayoutInflater().inflate(R.layout.search_panel,null);
        displayItemText = (TextView) displayItem.findViewById(R.id.search_item_name);
        displayUNText = (TextView) displayItem.findViewById(R.id.search_item_UN);
        displayButton = (Button) displayItem.findViewById(R.id.button_add_search_item);

        displayItemText.setText(itemName);
        displayUNText.setText("UN: " + UN);
      /*  displayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

        searchListContainer.addView(displayItem,0);
    }

}