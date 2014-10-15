package fg.hazmateasiermanagement;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import fg.hazmateasiermanagement.database.AccessDatabase;
import fg.hazmateasiermanagement.database.Database;

/**
 * Created by Magnus on 2014-10-01.
 * Simple activity that shows information(text) about selected element.
 */
public class ElementInformationActivity extends Activity {

    public static final String INTENT_INFORMATION_ID = "elementInfoID";
    private TextView informationText;
    private int elementId;
    private Database db = new Database(this);
    private AccessDatabase accessDatabase = new AccessDatabase(db);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        elementId = getIntent().getIntExtra(INTENT_INFORMATION_ID, 0);

        if(elementId == 0)
            onDestroy();

        String elementInformation = (String) accessDatabase.getElement(elementId).getDescription();

        informationText = (TextView) findViewById(R.id.informationText);

        informationText.setText(elementInformation);
        //informationText = database.getElementInformation(elementId);

    }

}
