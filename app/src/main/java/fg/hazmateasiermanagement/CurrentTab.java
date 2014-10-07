package fg.hazmateasiermanagement;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Magnus on 2014-10-01.
 */
public class CurrentTab extends Activity {

    private LinearLayout parentLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current);
        parentLayout = (LinearLayout) findViewById(R.id.current_layout);

        //Kunna lägga till en ämnespanel (yourLayout.addView(yourView, 0); för att lägga till på toppen av layouten
        //Get Checklist ska samla ihop alla värden (vikt) och sända till databasen

    }

    //Kunna lägga till en ämnespanel (yourLayout.addView(yourView, 0); för att lägga till på toppen av layouten
    private void addElementPanel(int id,  String name, String image){

    }

    /**
     * Removes it's parent when called
     * @param view The remove button view
     */
    private void removeElementPanel(View view){
        parentLayout.removeView((View) view.getParent());
    }

    private void generateCheckList(){

    }

}

        /*
        TextView tv = new TextView(this);
        tv.setTextSize(25);
        tv.setGravity(Gravity.CENTER_VERTICAL);
        tv.setText("This Is Current Activity");

        setContentView(tv);
        */