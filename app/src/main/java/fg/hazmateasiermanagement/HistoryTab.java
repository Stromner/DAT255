package fg.hazmateasiermanagement;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

/**
 * Created by Magnus on 2014-10-01.
 * Currently not used, just leaving it here incase things change.
 */
public class HistoryTab extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_history);

        /*
        Element element = (Element) getIntent().getSerializableExtra("fisk");

        TextView tv = new TextView(this);
        tv.setTextSize(25);
        tv.setGravity(Gravity.CENTER_VERTICAL);
        tv.setText("This Is History Activity");

        setContentView(tv);
        */
    }
}
