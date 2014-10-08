package fg.hazmateasiermanagement;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by Magnus on 2014-10-01.
 */
public class SearchTab extends Activity {

    /*
    private LinearLayout searchLayout;
    private ScrollView scrollView;
    private TableLayout tableLayout;
    private TableLayout tableLayout2;
    private TableLayout tableLayout3;
    private int elementPanel = R.layout.element_panel;
    */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


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
    }

}
