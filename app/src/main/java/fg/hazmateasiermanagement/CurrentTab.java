package fg.hazmateasiermanagement;

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
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by Magnus on 2014-10-01.
 */
public class CurrentTab extends Activity {

    private LinearLayout elementContainerLayout;
    private RelativeLayout outerLayout;
    private TextView textViewElementId;
    private TextView textViewElementName;
    private ImageView imageViewElementSign;
    private ImageButton removeButton;
    private Button getChecklistButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current);
        elementContainerLayout = (LinearLayout) findViewById(R.id.currentLayout);
        outerLayout = (RelativeLayout) findViewById(R.id.currentOuterLayout);

        //Example values
        addElementPanel(1234, "Diväveoxid", "alert");
        addElementPanel(2345, "vatten", "ic_launcher");
        addElementPanel(3456, "H2O", "alert");
        addElementPanel(4567, "Difyrdicoloxido", null);
    }

    /**
     * Adds a new tablelayout that contains id, name and sign for the element. It will also contain
     * a edit text meant for the weight (in Kg) of the transported element.
     * @param id ID number for the  element
     * @param name Scientific name for the element
     * @param image Accompanying sign for the element, if there is one.
     */
    private void addElementPanel(int id, String name, String image){
        TableLayout elementPanel;
        elementPanel = (TableLayout) getLayoutInflater().inflate(R.layout.element_panel, null);

        textViewElementId = (TextView) elementPanel.findViewById(R.id.elementId);
        textViewElementName = (TextView) elementPanel.findViewById(R.id.elementName);
        imageViewElementSign = (ImageView) elementPanel.findViewById(R.id.elementSign);
        removeButton = (ImageButton) elementPanel.findViewById(R.id.removeButton);

        removeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                removeElementPanel(view);
            }
        });

        if(!(image == null)){
            int id2 = getResources().getIdentifier(image, "drawable", getPackageName());
            Drawable drawable = getResources().getDrawable(id2);
            imageViewElementSign.setImageDrawable(drawable);
        }

        textViewElementId.setText("UN" + id);
        textViewElementName.setText(name);
        
        elementPanel.setBackgroundColor(getResources().getColor(R.color.dark_gray));

        //Self explanatory, this is added so the button isn't there when there is no elements in the list
        if(elementContainerLayout.getChildCount() == 0)
            addGetChecklistButton();

        elementContainerLayout.addView(elementPanel, 0);
    }

    /**
     * Removes it's parent when called
     * @param view The remove button view
     */
    public void removeElementPanel(View view){
        TableRow removeRow = (TableRow) view.getParent();
        TableLayout removeTable = (TableLayout) removeRow.getParent();
        elementContainerLayout.removeView(removeTable);

    }

    /**
     * Adds a button to the elementContainerLayout that will take you to the checklist for the selected elements.
     */
    private void addGetChecklistButton(){
        getChecklistButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //goToChecklistTab(view);
            }
        });

        outerLayout.addView(getChecklistButton);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        getChecklistButton.setLayoutParams(layoutParams);
        getChecklistButton.setText(R.string.getChecklist);
    }

    /**
     *
     */
    private void generateCheckList(){
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

    private void goTolementInformation(int id){
        Intent intent = new Intent(this, ElementInformationActivity.class);
        intent.putExtra(ElementInformationActivity.ID_KEY, id);
        startActivity(intent);
    }

}
