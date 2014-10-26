package fg.hazmateasiermanagement.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import fg.hazmateasiermanagement.R;
import fg.hazmateasiermanagement.database.Element;

/**
 * Activity to show information about an element.
 * @author Kallten, Magnus
 * @version 2014-10-22
 */
public class ElementInformationActivity extends Activity {

    public static final String INTENT_INFORMATION_ID = "elementInfoID";
    private TextView tvInformationText;
    private TextView tvInformationTitle;
    private Element element;
    private Button backButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        setupElementInformation();
    }

    /**
     * Receives an element object from currentTab and gives the activity a title and adds the information text.
     */
    private void setupElementInformation(){
        tvInformationText = (TextView) findViewById(R.id.tvInformationText);
        tvInformationTitle = (TextView) findViewById(R.id.tvInformationTitle);
        backButton = (Button) findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });

        element = (Element) getIntent().getSerializableExtra(INTENT_INFORMATION_ID);

        String elementInformationTitle = "UN" + element.getUNNumber() + " : " + element.getName();
        String elementInformation = element.getDescription();

        tvInformationTitle.setText(elementInformationTitle);
        tvInformationText.setText(elementInformation);
    }
}
