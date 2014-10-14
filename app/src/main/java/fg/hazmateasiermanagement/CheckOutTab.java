package fg.hazmateasiermanagement;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Magnus on 2014-10-01.
 */
public class CheckOutTab extends Activity {
    private LinearLayout checkoutLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        checkoutLayout = (LinearLayout) findViewById(R.id.checkoutLayout);
    }

    /**
     * Generates a point list with what you need to do based on selected elements.
     * @param list The string list of what needs to be done.
     */
    private void addCheckOutList(String[] list){
        String line;
        for(String point : list){
            TextView tvPoint = new TextView(this);
            tvPoint.setTextColor(getResources().getColor(R.color.white));
            line = "• " + point + "\n\n";
            tvPoint.setText(line);
            checkoutLayout.addView(tvPoint);
        }
    }

    private void emptyCheckoutList(){
        checkoutLayout.removeAllViews();
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
