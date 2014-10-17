package fg.hazmateasiermanagement.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import fg.hazmateasiermanagement.R;

/**
 * Currently useless, just leaving it here in case things change.
 *
 * @author Kallten, Magnus
 * @version 2014-10-17
 */
public class ElementInformationActivity extends Activity {

    public static final String ID_KEY = "fg.hazmateasiermanagement.hazmatID";
    private TextView informationText;
    private int elementId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        elementId = getIntent().getIntExtra(ID_KEY, 0);

        informationText = (TextView) findViewById(R.id.informationText);
        //informationText = database.getElementInformation(elementId);

    }

}
