package fg.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by Magnus on 2014-10-05.
 */
public class ElementView extends ViewGroup {

    private double weight;
    private String image;


    public ElementView(Context context, AttributeSet attributeSet){
        super(context, attributeSet);

    }

    @Override
    protected void onLayout(boolean b, int i, int i2, int i3, int i4) {

    }
}
