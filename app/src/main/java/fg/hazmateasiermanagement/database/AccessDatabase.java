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
 * @version 2014-10-11
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
            Cursor cursor = db.getCompleteDatabase();
            String s[] = new String[cursor.getCount()];
            int i = 0;
            while(!cursor.isAfterLast()){
                s[i] = "S"+cursor.getString(0)+"W0";
                cursor.moveToNext();
                i++;
            }
            //fullDatabase = stringToList(s);
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
        // TODO Extend this method once the database is complete so it can make use of the full constructor of element
        Cursor cursor = db.getElement(elementID);
        if(cursor == null){
            return null;
        }

        String arr[] = new String[cursor.getColumnCount()];
        int pos = 0;
        while(!cursor.isAfterLast()){
            arr[pos] = cursor.getString(pos);
            pos++;
        }
        return new Element(Integer.parseInt(arr[0]));
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
        return db.removeElement(elementID);
    }
}
