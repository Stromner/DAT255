package fg.hazmateasiermanagement;

import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
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

    private final String SHARED_PREF = "fg.hazmateasiermanagment.firstRun";
    private Database db;
    private AccessDatabase accessDatabase;
    private TabHost tabHost;
    private TabSpec tab1, tab2, tab3;
    private SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);

        if (sharedPreferences.getBoolean("firstRun", true)) {
            //Seed
            sharedPreferences.edit().putBoolean("firstRun", false).commit();
        }
        else{
            //Nothing?
        }
        */

        //db = new Database(this.getBaseContext());
        //accessDatabase = new AccessDatabase(db);
        //String label = (String) accessDatabase.getElement(4).getLabel();

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

        /*
        tab3 = tabHost.newTabSpec("Third tab");
        tab3.setIndicator("Checkout");
        //tab3.setContent(R.id.tabHistory);
        tab3.setContent(new Intent(this, CheckOutTab.class));
        tabHost.addTab(tab3);
        */
    }

    public AccessDatabase getAccessDatabase(){
        return accessDatabase;
    }
}