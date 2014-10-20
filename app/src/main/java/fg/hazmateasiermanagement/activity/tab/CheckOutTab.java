package fg.hazmateasiermanagement.activity.tab;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import fg.hazmateasiermanagement.ListWrapper;
import fg.hazmateasiermanagement.Utility;
import fg.hazmateasiermanagement.database.Element;
import fg.hazmateasiermanagement.R;

/**
 * @author Kallten, Magnus
 * @version 2014-10-17
 */
public class CheckOutTab extends Activity {
    private LinearLayout checkoutLayout;
    private ArrayList<Element> elementList;
    private Button backButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

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
     * Create a checklist to be displayed in the checkout tab. The method takes care of all the
     * processing so at return it can just be displayed.
     *
     * @param list to create a checkout list from.
     * @return checkout list to be displayed.
     */
    private String[] createChecklist(List<Element> list){
        String[] stringArray = new String[]{"ok"};
        Set<String> set = new LinkedHashSet<String>();
        set.add("To transport the list you need the following signs:");

        int pos = 0;
        for(Element e : list){
            String s = e.isCompatible(list);
            if(s.compareTo("ok") == 0){
                set.add("\t - " + e.getHazmatImage());
            }
            else{
                if(stringArray[0].compareTo("ok") == 0){
                    stringArray[0] = s;
                }
                stringArray = Utility.extendArray(stringArray, String.class);
                stringArray[stringArray.length-1] = s;
            }
            pos++;
        }

        return stringArray[0].compareTo("ok") == 0 ? set.toArray(new String[set.size()]):stringArray;
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
