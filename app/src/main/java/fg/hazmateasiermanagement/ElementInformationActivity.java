package fg.hazmateasiermanagement;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Magnus on 2014-10-01.
 * Simple activity that shows information(text) about selected element.
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
     *
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

        String elementInformationTitle = element.getUNNumber() + " : " + element.getName();
        String elementInformation = element.getDescription();

        tvInformationTitle.setText(elementInformationTitle);
        tvInformationText = (TextView) findViewById(R.id.tvInformationText);

        tvInformationText.setText(elementInformation);
    }
}
