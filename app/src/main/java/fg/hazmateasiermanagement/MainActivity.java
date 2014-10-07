package fg.hazmateasiermanagement;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.app.Activity;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.SearchView;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */

    //SWIPEBBAAAY
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addTabs();
        additionalInfoTest();
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

    private void additionalInfoTest(){
        setContentView(R.layout.activity_current);

        LinearLayout ll = (LinearLayout)findViewById(R.id.linear_main);

        TextView testText = new TextView(this);
        Button button1 = new Button(this);
        button1.setText("UN: 0001");
        Button button2 = new Button(this);
        button2.setText("UN: 0002");

        ll.addView(button1);
        ll.addView(button2);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://en.wikipedia.org/benny"));
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://en.wikipedia.org/benny"));
            }
        });



    }


}
