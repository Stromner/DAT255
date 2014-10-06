package fg.hazmateasiermanagement;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import java.util.ArrayList;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */

    //SWIPEBBAAAY
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addTabs();
        createSearchAdapter();
    }
    private void addTabs(){
        TabHost tabHost = (TabHost)findViewById(R.id.tabHost);
        tabHost.setup();

        TabSpec tab1 = tabHost.newTabSpec("First Tab");
        TabSpec tab2 = tabHost.newTabSpec("Second Tab");
        TabSpec tab3 = tabHost.newTabSpec("Third tab");

        tab1.setIndicator("Search");
        tab1.setContent(R.id.tabSearch);
        tabHost.addTab(tab1);

        tab2.setIndicator("Current");
        tab2.setContent(R.id.tabCurrent);
        tabHost.addTab(tab2);

        tab3.setIndicator("History");
        tab3.setContent(R.id.tabHistory);

        tabHost.addTab(tab3);
    }

    private void createSearchAdapter(){
        ArrayList<String> searchList = new ArrayList<String>();
        searchList.add("Testitem 1");
        searchList.add("Testitem 2");

        SearchDatabaseAdapter adapter = new SearchDatabaseAdapter(searchList, this);
        ListView lView = (ListView)findViewById(R.id.searchListView);
        lView.setAdapter(adapter);


    }
}
