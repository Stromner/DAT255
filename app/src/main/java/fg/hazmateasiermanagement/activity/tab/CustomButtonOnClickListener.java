package fg.hazmateasiermanagement.activity.tab;

import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;

/**
 * @author Benjamin Wijk
 * @version 2014-10-19
 */
public class CustomButtonOnClickListener implements View.OnClickListener {
    int uN;
    Button button;
    boolean added;

    public CustomButtonOnClickListener(int uN, Button button){
        this.uN = uN;
        this.button = button;
        added = false;
    }

    @Override
    public void onClick(View v) {

    }
}
