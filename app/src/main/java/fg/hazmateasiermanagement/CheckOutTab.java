package fg.hazmateasiermanagement;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Magnus on 2014-10-01.
 */
public class CheckOutTab extends Activity {
    private LinearLayout checkoutLayout;
    private ArrayList<Element> elementList;
    private Button backButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        Log.i("fisk","onCreate checkout activity");

        setupCheckout();
    }

    /**
     *
     */
    private void setupCheckout(){
        checkoutLayout = (LinearLayout) findViewById(R.id.checkoutLayout);
        backButton = (Button) findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });

        ListWrapper wrapper = (ListWrapper) getIntent().getSerializableExtra("elementListWrapper");
        if(!(wrapper == null)) {
            elementList = wrapper.getElementList();
            addCheckoutList(createChecklist(elementList));
        }
    }

    /**
     *
     * @param list
     * @return
     */
    private String[] createChecklist(List<Element> list){
        String[] s = new String[list.size()];
        int pos = 0;
        for(Element e : list){
            s[pos] = e.isCompatible(list);
            pos++;
        }
        return s;
    }

    /**
     * Generates a point list with what you need to do based on selected elements.
     * @param list The string list of what needs to be done.
     */
    private void addCheckoutList(String[] list){
        String line;
        for(String point : list){
            TextView tvPoint = new TextView(this);
            tvPoint.setTextColor(getResources().getColor(R.color.white));
            line = point;
            tvPoint.setText(line);
            checkoutLayout.addView(tvPoint);
        }
    }

    /**
     * Emptys the layout.
     */
    private void emptyCheckoutList(){
        checkoutLayout.removeAllViews();
    }

}
