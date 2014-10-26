package fg.hazmateasiermanagement;

import java.io.Serializable;
import java.util.ArrayList;

import fg.hazmateasiermanagement.database.Element;

/**
 * Created by Magnus on 2014-10-15.
 */
public class ListWrapper implements Serializable{

    private ArrayList<Element> elementList;

    public ListWrapper(ArrayList<Element> elementList) {
        this.elementList = elementList;
    }

    public ArrayList<Element> getElementList() {
        return this.elementList;
    }

}
