package fg.hazmateasiermanagement.database;

import java.util.List;

import fg.hazmateasiermanagement.Element;
import fg.hazmateasiermanagement.database.Database;

/**
 * Factory class for Database, contains methods to modify and access the database in an easy way.
 *
 * @author Johansson, Henrik
 * @author Stromner, David
 * @version 2014-10-09
 */

public class AccessDatabase {
    private Database db;
    private List<Element> fullDatabase;

    public AccessDatabase(Database db){
        this.db = db;
    }

    /**
     * Returns the entire database in a list consisting of elements.
     *
     * @return database in element format.
     */
    public List<Element> getCompleteDatabase(){
        if(fullDatabase == null){

        }
        return fullDatabase;
    }

    /**
     * Check if a list of cargo can be transported together.
     *
     * @param list to be checked.
     * @return true if the list can be transported together, false otherwise.
     */
    public Boolean checkCargoList(String[] list){
        stringToList(list);
        return false;
    }

    /**
     * Converts a String array to a list of elements. The format on the string array must be
     * as following: "{E_W_,E_W_}" where the empty spaces must represent an integer number and E
     * stands for Element and W for Weight.
     *
     * @param list to be converted.
     * @return Converted list, null if format exception occurs.
    */
    private List<Element> stringToList(String[] list){
        return null;
    }

    /**
     * Calls on the database.addElement method.
     *
     * @param unID the UN identification number for the element.
     * @param name the proper name for the element.
     * @return true if it succeeded, false otherwise.
     */
    public Boolean addElement(int unID, String name){
        return db.addElement(unID, name);
    }

    /**
     * Calls on the database.removeElement method.
     *
     * @param elementID element to remove.
     * @return true if the element was removed, false otherwise.
     */
    public Boolean removeElement(int elementID){
        return db.removeElement(elementID);
    }

    /**
     * Calls on the database.getElement method, convert the cursor that it returns to an element
     * class for easier access.
     *
     * @param elementID
     * @return
     */
    public Element getElement(int elementID){
        return null;
    }
}
