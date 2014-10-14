package fg.hazmateasiermanagement;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Magnus on 2014-10-01.
 * Currently useless, just leaving it here incase things change.
 */
public class CheckOutTab extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_checkout);

        TextView tv = new TextView(this);
        tv.setTextSize(25);
        tv.setGravity(Gravity.CENTER_VERTICAL);
        tv.setText("This Is The Check Out Activity");

        setContentView(tv);
    }

    private String[] createChecklist(List<Element> list){
        String s[] = new String[list.size()];
        int pos = 0;
        for(Element e: list){
            s[pos] = e.checkCompatibility(list);
            pos++;
        }
        return s;
    }
}
