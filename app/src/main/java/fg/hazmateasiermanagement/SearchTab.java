package fg.hazmateasiermanagement;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Benjamin on 2014-10-01.
 */
public class SearchTab extends Activity {

    LinearLayout searchListContainer;
    private LinearLayout searchLayout;
    private int elementPanel = R.layout.element_panel;
    EditText searchBar;

    LinkedList<String> searchList;
    LinkedList<String> searchListDisplay;
    LinkedList<String> searchListDisplayRemoval;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchListContainer = (LinearLayout) findViewById(R.id.search_list);



        searchLayout = (LinearLayout) findViewById(R.id.search_layout);
        searchList = new LinkedList<String>();
        searchListDisplay = new LinkedList<String>();

        //EXAMPLES
        addUNItems("Combat missile");
        addUNItems("Combattu missile");
        addUNItems("beni missile 1");
        addUNItems("beni missile 57");
        addUNItems("beni missile 15");
        addUNItems("Kall pop 03");

        setupSearch();


    }

    private void setupSearch(){
        searchBar = (EditText) findViewById(R.id.search_text);

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                searchListDisplay.clear();
                if (s.toString().isEmpty() == false) {
                String search = ".*" + s.toString().toLowerCase() + ".*";

                    for (String temp : searchList) {
                        if (temp.toLowerCase().matches(search)) {
                            searchListDisplay.add(temp);
                        }
                    }

                }
                updateDisplay();
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

    }


    private void addUNItems(String itemName){
        searchList.add(itemName);
    }

    private void addListDisplayItem(String s, int counter){
        TextView displayItemText;
        TextView displayUNText;
        TextView displayButton;

        LinearLayout displayItem = (LinearLayout) getLayoutInflater().inflate(R.layout.search_panel,null);
        displayItemText = (TextView) displayItem.findViewById(R.id.search_item_name);
        displayUNText = (TextView) displayItem.findViewById(R.id.search_item_UN);
        displayButton = (Button) displayItem.findViewById(R.id.button_add_search_item);

        displayItemText.setText(s);
        displayUNText.setText("Temp nr: " + counter);
      /*  displayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

        searchListContainer.addView(displayItem,0);
    }


    private void updateDisplay(){
        searchListContainer.removeAllViews();
        int tempCounter = 0;
        for(String temp: searchListDisplay) {
            tempCounter++;
            addListDisplayItem(temp, tempCounter);
        }
    }


}



 /*
        HashMap<String, ImageView> map;

        ImageView image = (ImageView) findViewById(R.id.elementSign);

        //Used for testing of GUI

        searchLayout = (LinearLayout) findViewById(R.id.search_layout);

        tableLayout = (TableLayout) getLayoutInflater().inflate(elementPanel, null);
        tableLayout.setBackgroundColor(getResources().getColor(R.color.blue));
        searchLayout.addView(tableLayout);

        tableLayout2 = (TableLayout) getLayoutInflater().inflate(elementPanel, null);
        tableLayout2.setBackgroundColor(getResources().getColor(R.color.purple));
        searchLayout.addView(tableLayout2);

        tableLayout3 = (TableLayout) getLayoutInflater().inflate(elementPanel, null);
        tableLayout3.setBackgroundColor(getResources().getColor(R.color.blue));
        searchLayout.addView(tableLayout3);
        */

        /*
        TextView tv = new TextView(this);
        tv.setTextSize(25);
        tv.setGravity(Gravity.CENTER_VERTICAL);
        tv.setText("This Is Search Activity");

        setContentView(tv);
        */