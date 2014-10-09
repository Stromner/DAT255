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

    public AccessDatabase(Database db){
        this.db = db;
    }

    /**
     * Returns the entire database in a list consisting of elements.
     *
     * @return database in element format.
     */
    public List<Element> getCompleteDatabase(){
        return null;
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
     * @param UN_ID the UN identification number for the element.
     * @param NAME the proper name for the element.
     * @return true if it succeeded, false otherwise.
     */
    public Boolean addElement(int UN_ID, String NAME){
        return db.addElement(UN_ID, NAME);
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
}
