package fg.hazmateasiermanagement.database;

import android.database.Cursor;
import java.util.IllegalFormatException;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

import fg.hazmateasiermanagement.Element;
import fg.hazmateasiermanagement.database.Database;

/**
 * Factory class for Database, contains methods to modify and access the database in an easy way.
 *
 * @author Johansson, Henrik
 * @author Stromner, David
 * @version 2014-10-14
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
     * @return database in element format. null if the database contains no elements.
     */
    public List<Element> getCompleteDatabase(){
        if(fullDatabase == null){
            Cursor cursor = db.getCompleteDatabase();
            if(cursor == null){
                return fullDatabase;
            }

            LinkedList<Element> list= new LinkedList<Element>();
            while(!cursor.isAfterLast()){
                list.add(getElement(cursor.getInt(0)));
            }
            fullDatabase = list;
        }
        return fullDatabase;
    }

    /**
     * Calls on the database.getElement method, convert the cursor that it returns to an element
     * class for easier access.
     *
     * @param elementID element to be fetched.
     * @return the Element version of the cursor if 'elementID' exist, null otherwise.
     */
    public Element getElement(int elementID){
        Cursor cursor = db.getElement(elementID);
        if(cursor == null){
            return null;
        }

        String arr[] = new String[cursor.getColumnCount()];
        int pos = 0;
        while(pos<cursor.getColumnCount()){
            arr[pos] = cursor.getString(pos);
            pos++;
        }
        return new Element(Integer.parseInt(arr[0]), arr[1], arr[2], arr[3], arr[4], arr[5]);
    }

    /**
     * Calls on the database.addElement method.
     *
     * @param unID the UN identification number for the element.
     * @param name the proper name for the element.
     * @param description describe the Element in detail.
     * @param label used to show what material that can be shipped together.
     * @param hazmat_image file name of what image(if any) applies to this element).
     * @param not_compatible shows which labels this element cannot be shipped with.
     * @return true if it succeeded, false otherwise.
     */
    public Boolean addElement(int unID, String name, String description, String label, String hazmat_image, List<String> not_compatible){
        return db.addElement(unID, name, description, label, hazmat_image, not_compatible);
    }

    /**
     * Calls on the database.removeElement method.
     *
     * @param elementID element to remove.
     * @return true if the element was removed, false otherwise.
     */
    public Boolean removeElement(int elementID){
        if(fullDatabase != null && db.removeElement(elementID)){
            fullDatabase.remove(elementID);
            return true;
        }
        return false;
        //return db.removeElement(elementID);
    }
}
