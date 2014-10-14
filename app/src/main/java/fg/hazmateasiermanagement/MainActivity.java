package fg.hazmateasiermanagement;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import fg.hazmateasiermanagement.database.AccessDatabase;
import fg.hazmateasiermanagement.database.Database;

/**
 * Created by Magnus on 2014-10-01.
 * The "Main" class, basically just contains the tabs.
 */

public class MainActivity extends TabActivity {

    private Database db;
    private AccessDatabase accessDatabase;
    private TabHost tabHost;
    private TabSpec tab1, tab2, tab3;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new Database(this);
        accessDatabase = new AccessDatabase(db);

        addTabs();
    }

    private void addTabs(){
        //(TabHost)findViewById(R.id.tabHost);
        tabHost = getTabHost();
        tabHost.setup();

        tab1 = tabHost.newTabSpec("First Tab");
        tab1.setIndicator("Search");
        //tab1.setContent(R.id.tabSearch);
        tab1.setContent(new Intent(this, SearchTab.class));
        tabHost.addTab(tab1);

        tab2 = tabHost.newTabSpec("Second Tab");
        tab2.setIndicator("Current");
        //tab2.setContent(R.id.tabCurrent);
        tab2.setContent(new Intent(this, CurrentTab.class));
        tabHost.addTab(tab2);

        tab3 = tabHost.newTabSpec("Third tab");
        tab3.setIndicator("History");
        //tab3.setContent(R.id.tabHistory);
        tab3.setContent(new Intent(this, HistoryTab.class));
        tabHost.addTab(tab3);
    }

    public AccessDatabase getAccessDatabase(){
        return accessDatabase;
    }
}