package fg.hazmateasiermanagement;

import java.util.List;
import fg.hazmateasiermanagement.Element;

/**
 * Contains all the UN elements that the application knows about and support for modifying the
 * database. Support saving the entire database locally or saving a smaller list of elements in a
 * list. Provided with a list of elements the database can check if the list would be okay to be
 * transported with each other.
 *
 * @author Johansson, Henrik
 * @author Strömner, David
 * @version 2014-09-24
 */

public class Database {
    public Boolean addElement(Element element){
        return false;
    }
    public Boolean removeElement(Element element){
	//Testing changing something in method
        return false;
    }
    public Element getElement(int position){
        return null;
    }
    public Boolean checkList(Database database){
        return false;
    }
    public List getList(){
        return null;
    }
}
