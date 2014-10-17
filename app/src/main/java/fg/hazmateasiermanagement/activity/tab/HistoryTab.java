package fg.hazmateasiermanagement.activity.tab;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

import fg.hazmateasiermanagement.R;

/**
 * Currently useless, just leaving it here incase things change.
 *
 * @author Kallten, Magnus
 * @version 2014-10-17
 */
public class HistoryTab extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_history);

        TextView tv = new TextView(this);
        tv.setTextSize(25);
        tv.setGravity(Gravity.CENTER_VERTICAL);
        tv.setText("This Is History Activity");

        setContentView(tv);
    }
}
