package fg.hazmateasiermanagement;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Magnus on 2014-10-01.
 */
public class CurrentTab extends Activity {

    private LinearLayout elementContainerLayout;
    private ScrollView outerLayout;
    private Button getChecklistButton;
    private List<Element> elementList = new ArrayList<Element>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current);
        elementContainerLayout = (LinearLayout) findViewById(R.id.currentLayout);
        outerLayout = (ScrollView) findViewById(R.id.currentScrollView);
        getChecklistButton = (Button) findViewById(R.id.getChecklistButton);
        getChecklistButton.setVisibility(View.INVISIBLE);


        //Need to load elements if there are any active here.

        //Example values
        addElementPanel(new Element(1234, "Diväveoxid", "Diväteoxid beskrivning", "label", "ic_launcher", "comp"));
        addElementPanel(new Element(2345, "Vatten", "Diväteoxid beskrivning", "label", "ic_launcher", "comp"));
        addElementPanel(new Element(3456, "H2O", "Diväteoxid beskrivning", "label", "ic_launcher", "comp"));
        addElementPanel(new Element(4567, "Fiskvatten", "Diväteoxid beskrivning", "label", "ic_launcher", "comp"));
    }

    /**
     * Adds a new tablelayout that containing information about the element. It will also contain
     * a edit text meant for the weight (in Kg) of the transported element.
     * @param element An object of the selected element.
     */
    private void addElementPanel(Element element) {
        //Makes sure there aren't more than one element of the same typ in the lists.

        for(Element el : elementList){
            if(el.getUNNumber() == element.getUNNumber()){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Look at this dialog!")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //Do nothing
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
                return;
            }
        }

        elementList.add(element);
        TableLayout elementPanel = (TableLayout) getLayoutInflater().inflate(R.layout.element_panel, null);

        TextView textViewElementId = (TextView) elementPanel.findViewById(R.id.elementId);
        TextView textViewElementName = (TextView) elementPanel.findViewById(R.id.elementName);
        ImageView imageViewElementSign = (ImageView) elementPanel.findViewById(R.id.elementSign);
        ImageButton removeButton = (ImageButton) elementPanel.findViewById(R.id.removeButton);

        removeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                removeElementPanel(view);
            }
        });

        //If there is an image for the element it will change the sign for that image (Otherwise it will stay as the standard)
        if(!(element.getHazmatImage() == null)){
            String image = "@drawable/" + element.getHazmatImage();
            int drawableID = getResources().getIdentifier(image, null, getPackageName());
            Drawable drawable = getResources().getDrawable(drawableID);
            imageViewElementSign.setImageDrawable(drawable);
        }

        textViewElementId.setText(String.valueOf(element.getUNNumber()));
        textViewElementName.setText(element.getName());

        //elementPanel.setBackgroundColor(getResources().getColor(R.color.dark_gray));

        //Makes the getChecklistButton visable when there is a element or more in the list
        if(elementList.size() == 1) {
            getChecklistButton.setVisibility(View.VISIBLE);
        }

        elementContainerLayout.addView(elementPanel, 0);
    }

    /**
     * Removes it's parent when called
     * @param view The remove button view
     */
    public void removeElementPanel(View view){
        TableRow removeRow = (TableRow) view.getParent();
        TextView tvRemoveUNNumber = (TextView) removeRow.getChildAt(1);

        String stringRemoveUNNumber = String.valueOf(tvRemoveUNNumber.getText());

        int removeUNNumber = Integer.parseInt(stringRemoveUNNumber);

        //Removes the element from the  elementList
        Iterator<Element> iterator = elementList.iterator();
        while (iterator.hasNext()) {
            Element element = iterator.next();
            if(element.getUNNumber() == removeUNNumber)
                iterator.remove();
        }
        for(Element element : elementList){
            if(element.getUNNumber() == removeUNNumber)
                elementList.remove(element);
        }

        TableLayout removeTable = (TableLayout) removeRow.getParent();
        elementContainerLayout.removeView(removeTable);

        if(elementList.size() == 0) {
            getChecklistButton.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Adds a button to the elementContainerLayout that will take you to the checklist for the selected elements.
     */

    // FIX: button is in a relativelayout outside scrolllayout now
    private void addGetChecklistButton(){
        Button getChecklistButton = new Button(this);
        getChecklistButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //generateChecklist();
                //goToChecklistTab(view);
            }
        });

        outerLayout.addView(getChecklistButton);
        //LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        RelativeLayout.LayoutParams relPar = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        relPar.addRule(Gravity.BOTTOM);
        getChecklistButton.setLayoutParams(relPar);
        getChecklistButton.setText(R.string.getChecklist);
    }

    /**
     *
     */
    private void generateChecklist(){
        int noElements;
        String id;
        String weight;
        TableRow row;
        String[] elementList;
        EditText weightEditText;
        TextView elementIdTextView;
        TableLayout elementPanel;

        //Gets the number of elements from the layout, - 1 because of the get checklist button.
        noElements = elementContainerLayout.getChildCount() - 1;
        elementList = new String[noElements];

        for(int i = 0; i<= noElements; i++){
            elementPanel = (TableLayout) elementContainerLayout.getChildAt(i);

            row = (TableRow) elementPanel.getChildAt(0);
            elementIdTextView = (TextView) row.getChildAt(1);
            id = elementIdTextView.getText().toString();

            row = (TableRow) elementPanel.getChildAt(2);
            weightEditText = (EditText) row.getChildAt(0);
            weight = weightEditText.getText().toString().trim();

            //Send in format: String[] list = {E12345W95, E12345W67, E12345W54}
            //E(lement)12345(id)W(eight)95(in kg)
            elementList[i] = ("E" + id + "W" + weight);
        }
        //Intent to go to check list
    }

    private void generateChecklist2(){
        int noElements = elementContainerLayout.getChildCount() - 1;
        List<String> tempElementList = new ArrayList<String>();
        for(Element element : elementList){
            //Vill dom fortfarande ha en lista?
            tempElementList.add("E" + element.getUNNumber() + "W" + element.getWeight());
        }

    }

    private void goTolementInformation(int id){
        Intent intent = new Intent(this, ElementInformationActivity.class);
        intent.putExtra(ElementInformationActivity.ID_KEY, id);
        startActivity(intent);
    }

}
