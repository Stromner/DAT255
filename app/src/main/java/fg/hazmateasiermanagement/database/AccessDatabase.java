package fg.hazmateasiermanagement.database;

import android.database.Cursor;

import java.util.IllegalFormatException;
import java.util.LinkedList;
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
            Cursor cursor = db.getCompleteDatabase();
            String s[] = new String[cursor.getCount()];
            int i = 0;
            while(!cursor.isAfterLast()){
                s[i] = "S"+cursor.getString(0)+"W0";
                cursor.moveToNext();
                i++;
            }
            fullDatabase = stringToList(s);
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
     * Converts a String array to a list of elements. The format on the string array must be
     * as following: "{E_W_,E_W_}" where the empty spaces must represent an integer number and E
     * stands for Element and W for Weight.
     *
     * @param list to be converted.
     * @return Converted list, null if the list in is null.
     * @throws IllegalArgumentException if format exception occurs. Or if the list contains
     * a non-existing element.
     */
    private List<Element> stringToList(String[] list) throws IllegalArgumentException{
        if(list == null){
            return null;
        }
        LinkedList<Element> linkedList = new LinkedList<Element>();

        for(int i=0;i<list.length;i++){
            int e = list[i].indexOf('E');
            int w = list[i].indexOf('W');
            if(e == -1 || w == -1){
                // TODO change exception type
                throw new IllegalArgumentException("Couldn't find both 'E' and 'W'");
            }

            int elementID = Integer.parseInt(list[i].substring(e+1, w));
            int weight = Integer.parseInt(list[i].substring(w+1));
            Element element = getElement(elementID);
            if(element == null){
                // TODO change exception type
                throw new IllegalArgumentException("Contains non-existing element");
            }

            element.setWeight(weight);
            linkedList.add(element);
        }

        return linkedList;
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
}
