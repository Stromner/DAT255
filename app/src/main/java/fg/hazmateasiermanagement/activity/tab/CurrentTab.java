package fg.hazmateasiermanagement.activity.tab;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

import fg.hazmateasiermanagement.ListWrapper;
import fg.hazmateasiermanagement.activity.CheckoutActivity;
import fg.hazmateasiermanagement.activity.ElementInformationActivity;
import fg.hazmateasiermanagement.database.Element;
import fg.hazmateasiermanagement.R;

/**
 * @author Kallten, Magnus
 * @version 2014-10-22
 */
public class CurrentTab extends Activity {

    private LinearLayout elementContainerLayout;
    private Button getChecklistButton;
    private ArrayList<Element> elementList = new ArrayList<Element>();
    public static final String INTENT_INFORMATION_ID = "elementInfoID";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current);
        elementContainerLayout = (LinearLayout) findViewById(R.id.currentLayout);
        getChecklistButton = (Button) findViewById(R.id.getChecklistButton);
        getChecklistButton.setVisibility(View.INVISIBLE);

        /*
        //Example values copied from Seed
        addElementPanel(new Element(2909, "URANIUM", "RADIOACTIVE MATERIAL", 1f, "5.2", "corrosive", "1;1.4;1.5;1.6;2.1;2.2;2.3;3;4.1;4:2;4.3;5.2;6.1;6.2;7;8;9"));
        addElementPanel(new Element(1541, "ACETONE CYANOHYDRIN", "(STABILIZED)", 2f, "6.1", "asdasd", "1;1.4;1.5;1.6;2.1;2.2;2.3;4.1;5.2"));
        addElementPanel(new Element(1474, "MAGNESIUM NITRATE", "SALT", 3f, "5.1", "ic_launcher", "1;1.4;1.5;1.6;2.1;2.2;2.3;3;4.1;4:2;4.3;5.2;6.1;6.2;7;8;9"));
        addElementPanel(new Element(4, "AMMONIUM PICTRATE", "Dry or wetted with less than 10% water, by mass", 4f, "2.1", "ic_launcher", "1;1.4;1.5;1.6;4.1;5.2"));
        */

    }

    /**
     * Adds a new tablelayout that containing information about the element. It will also contain
     * a edit text meant for the weight (in Kg) of the transported element.
     * @param element An object of the selected element.
     * @return False if the element is already in the list, otherwise true;
     */

    public boolean addElementPanel(final Element element) {
        //Makes sure there aren't more than one element of the same typ in the lists.
        for(Element el : elementList){
            if(el.getUNNumber() == element.getUNNumber())
                return false;
        }

        elementList.add(element);
        TableLayout elementPanel = (TableLayout) getLayoutInflater().inflate(R.layout.element_panel, null);

        TextView textViewElementId = (TextView) elementPanel.findViewById(R.id.elementId);
        TextView textViewElementName = (TextView) elementPanel.findViewById(R.id.elementName);
        ImageView imageViewElementSign = (ImageView) elementPanel.findViewById(R.id.elementSign);
        ImageButton removeButton = (ImageButton) elementPanel.findViewById(R.id.removeButton);
        ImageButton informationButton = (ImageButton) elementPanel.findViewById(R.id.goToInformationButton);

        informationButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                goToElementInformation(element);
            }
        });

        removeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                removeElementPanel(view);
            }
        });

        //If there is an image for the element it will change the sign for that image (Otherwise it will stay as the standard)
        if(!(element.getHazmatImage() == null)){
            if(!(getResources().getIdentifier(element.getHazmatImage(), "drawable", getPackageName()) == 0)){
                String image = "@drawable/" + element.getHazmatImage();
                int drawableID = getResources().getIdentifier(image, null, getPackageName());
                Drawable drawable = getResources().getDrawable(drawableID);
                imageViewElementSign.setImageDrawable(drawable);
            }
        }

        textViewElementId.setText(String.valueOf(element.getUNNumber()));
        textViewElementName.setText(element.getName());

        //Makes the getChecklistButton visable when there is a element or more in the list
        if(elementList.size() == 1) {
            getChecklistButton.setVisibility(View.VISIBLE);
        }

        elementContainerLayout.addView(elementPanel, 0);

        return true;
    }

    /**
     * Removes the buttons parentView, i.e, the elementPanel.
     * Most of the code is for removing the selected element from the elementList though.
     * @param view The remove button view
     */
    public void removeElementPanel(View view){
        //Code very much based on exactly knowing the xml file.
        TableRow removeRow = (TableRow) view.getParent();
        TextView tvRemoveUNNumber = (TextView) removeRow.getChildAt(1);

        //Gets the UN number so we can use it to remove the element from the elementList
        String stringRemoveUNNumber = String.valueOf(tvRemoveUNNumber.getText());
        int removeUNNumber = Integer.parseInt(stringRemoveUNNumber);

        TableLayout removeTable = (TableLayout) removeRow.getParent();
        elementContainerLayout.removeView(removeTable);

        //Removes the element from the  elementList
        Iterator<Element> iterator = elementList.iterator();
        while (iterator.hasNext()) {
            Element element = iterator.next();
            if(element.getUNNumber() == removeUNNumber)
                iterator.remove();
        }

        //Makes the getChecklistButton invisible if there are no elements in the list.
        if(elementList.size() == 0) {
            getChecklistButton.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Will add the weights from the editable text input to the elementList before sending the list to the checkout activity.
     */
    public void checkout(View view) {
        //Don't really need this check since the button is invisible when there are no elements in the list.
        if (elementContainerLayout.getChildCount() == 0)
            return;

        int noElementPanels = elementContainerLayout.getChildCount();
        TableLayout elementPanel;
        TableRow row;
        EditText et;
        float weight;

        for (int i = 0; i < noElementPanels; i++) {
            elementPanel = (TableLayout) elementContainerLayout.getChildAt(i);
            row = (TableRow) elementPanel.getChildAt(2);
            et = (EditText) row.getChildAt(0);

            try {
                weight = Float.valueOf(et.getText().toString());
            } catch (NumberFormatException e) {
                weight = 0;
            }

            elementList.get(i).setWeight(weight);
        }
        Intent intent = new Intent(this, CheckoutActivity.class);
        intent.putExtra("elementListWrapper", new ListWrapper(elementList));
        startActivity(intent);
    }

    /**
     * Sends an element object to the ElementInformationActivity so it can read the id, name and information from the element without consulting the database.
     * @param e The element being sent.
     */
    private void goToElementInformation(Element e){
        Intent intent = new Intent(this, ElementInformationActivity.class);
        intent.putExtra(INTENT_INFORMATION_ID, e);
        startActivity(intent);
    }

}
