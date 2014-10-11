package fg.hazmateasiermanagement.database;

import java.util.ArrayList;

/**
 * Shows if two labels are compatible together
 * Created by Henrik on 2014-10-11.
 */
public class CompatibleLabels {
    private String label1,label2;
    private Boolean compatible;

    CompatibleLabels(String label1, String label2, Boolean compatible){
        this.label1 = label1;
        this.label2 = label2;
        this.compatible = compatible;
    }

    public String getLabel1() {
        return label1;
    }

    public String getLabel2() {
        return label2;
    }

    public Boolean getCompatible() {
        return compatible;
    }
}
