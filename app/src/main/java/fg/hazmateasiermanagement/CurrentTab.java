package fg.hazmateasiermanagement;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    private Button getChecklistButton;
    private List<Element> elementList = new ArrayList<Element>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current);
        elementContainerLayout = (LinearLayout) findViewById(R.id.currentLayout);
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
     *
     */
    //Add alertdialog that prompts input name for save
    //Send saved name and date/time
    private void sendElementList(){
        String saveName;

        Intent intent = new Intent(this, CheckOutTab.class);
        intent.putExtra("currentElementList", (ArrayList<Element>) elementList);

    }

    //Finish this and its respective activity
    private void goTolementInformation(int id){
        Intent intent = new Intent(this, ElementInformationActivity.class);
        intent.putExtra(ElementInformationActivity.ID_KEY, id);
        startActivity(intent);
    }

}
