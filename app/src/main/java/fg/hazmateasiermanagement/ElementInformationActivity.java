package fg.hazmateasiermanagement;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Magnus on 2014-10-01.
 * Currently useless, just leaving it here incase things change.
 */
public class ElementInformationActivity extends Activity {

    private TextView informationText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        informationText = (TextView) findViewById(R.id.informationText);
        //informationText = database.getInformation();

    }

}
