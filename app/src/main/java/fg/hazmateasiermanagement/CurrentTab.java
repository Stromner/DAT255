package fg.hazmateasiermanagement;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by Magnus on 2014-10-01.
 */
public class CurrentTab extends Activity {

    private LinearLayout elementContainerLayout;
    private TextView textViewElementId;
    private TextView textViewElementName;
    private ImageView imageViewElementSign;
    private ImageButton removeButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current);
        elementContainerLayout = (LinearLayout) findViewById(R.id.current_layout);

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
    
    //Send in format: String[] list = {E12345W95, E12345W67, E12345W54}
    //E(lement)12345(id)W(eight)95(in kg)
    private void generateCheckList(){
        int i = elementContainerLayout.getChildCount();
        //Intent to go to check list

    }

    private void goTolementInformation(int id){
        Intent intent = new Intent(this, ElementInformationActivity.class);
        intent.putExtra(ElementInformationActivity.ID_KEY, id);
        startActivity(intent);
    }

}
