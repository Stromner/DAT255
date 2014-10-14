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
            LinkedList<Element> list= new LinkedList<Element>();
            Cursor cursor = db.getCompleteDatabase();
            if(cursor == null){
                fullDatabase = list;
                return null;
            }

            while(!cursor.isAfterLast()){
                list.add(getElement(cursor.getInt(0)));
                cursor.moveToNext();
            }
            cursor.close();
            fullDatabase = list;
        }
        return fullDatabase;
    }

    /**
     * Calls on the database.getElement method, convert the cursor that it returns to an element
     * class for easier access.
     *
     * @param unID element to be fetched.
     * @return the Element version of the cursor if 'elementID' exist, null otherwise.
     */
    public Element getElement(int unID){
        Cursor cursor = db.getElement(unID);
        if(cursor == null){
            return null;
        }

        String arr[] = new String[cursor.getColumnCount()];
        int pos = 0;
        while(pos<cursor.getColumnCount()){
            arr[pos] = cursor.getString(pos);
            pos++;
        }
        cursor.close();
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
        if(fullDatabase == null){
            getCompleteDatabase();
        }
        boolean result = db.addElement(unID, name, description, label, hazmat_image, not_compatible);
        if(result) {
            fullDatabase.add(getElement(unID));
        }
        return result;
    }

    /**
     * Calls on the database.removeElement method.
     *
     * @param unID element to remove.
     * @return true if the element was removed, false otherwise.
     */
    public Boolean removeElement(int unID){
        if(fullDatabase == null) {
            getCompleteDatabase();
        }
        boolean result = db.removeElement(unID);
        for(Element e : fullDatabase){
            if(e.getUNNumber() == unID){
                fullDatabase.remove(e);
            }
        }
        return result;
    }
}
