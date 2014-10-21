package fg.hazmateasiermanagement.activity;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

import fg.hazmateasiermanagement.R;
import fg.hazmateasiermanagement.database.AccessDatabase;
import fg.hazmateasiermanagement.database.Database;
import fg.hazmateasiermanagement.database.Element;
import fg.hazmateasiermanagement.database.Seed;
import fg.hazmateasiermanagement.activity.tab.CurrentTab;
import fg.hazmateasiermanagement.activity.tab.HistoryTab;
import fg.hazmateasiermanagement.activity.tab.SearchTab;

/**
 * The "Main" class, contains the tabs and some shared resources.
 *
 * @author Kallten, Magnus
 * @author Wijk, Benjamin
 * @version 2014-10-21
 */

public class MainActivity extends TabActivity {

    private Database db;
    private AccessDatabase accessDatabase;
    private TabHost tabHost;
    private TabSpec tab1, tab2;

    private Intent intTab1, intTab2;



    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new Database(this.getApplicationContext());
        Seed seed = Seed.getInstance();
        seed.seedElements(db);
        accessDatabase = new AccessDatabase(db);

        addTabs();
    }

    private void addTabs(){

        tabHost = getTabHost();
        tabHost.setup();

        intTab1 = new Intent(this, SearchTab.class);
        intTab2 = new Intent(this, CurrentTab.class);

        intTab1.putExtra("db", accessDatabase);

        tab1 = tabHost.newTabSpec("First Tab");
        tab1.setIndicator("Search");
        tab1.setContent(intTab1);

        tab2 = tabHost.newTabSpec("Second Tab");
        tab2.setIndicator("Current");
        tab2.setContent(intTab2);

        tabHost.addTab(tab2);
        tabHost.addTab(tab1);

        //Initializes Current and SearchTab
        tabHost.setCurrentTab(0);
        tabHost.setCurrentTab(1);

    }

    public Activity getCurrentTab(){
        return getLocalActivityManager().getActivity("Second Tab");
    }

}