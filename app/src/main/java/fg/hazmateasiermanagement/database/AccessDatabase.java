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
    private List<CompatibleLabels> labelCompatibilityGrid;

    public AccessDatabase(Database db){
        this.db = db;
        labelCompatibilityGrid = setupLabelCompatibilityGrid();
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
     * @return true if the list can be transported together, false if cannot be shipped together,
     * null if illegal string in or un id does not exist in database.
     */
    public Boolean checkCargoList(String[] list){
        List<Element> elementList = stringToList(list);
        if(elementList == null){
            return null;
        }

        List<String> labelList = new ArrayList<String>();                                           // Create a list with all labels so we can check them together.
        int numberOfElements = elementList.size();
        for(int i=0; i<numberOfElements; i++){
            labelList.add(i,elementList.get(i).getLabel());
        }

        Boolean compatible;
        for(int i=0; i<numberOfElements; i++){                                                      // Check all the labels to see if they work together.
            for(int j=i; j<numberOfElements; j++){
                compatible = checkLabelCompatibility(labelList.get(i), labelList.get(j));
                if(compatible == null){
                    return null;
                }
                else if(compatible==false){
                    return  false;                                                                  // Unable to ship these together, TODO this should be saved somewhere so we can see what is wrong. Also we don't check rest of cargo
                }
            }
        }

        return true;
    }

    /**
     * Tests if two elements can be shipped together
     * @param label1
     * @param label2
     * @return true if the two elements can be shipped together, false if they cannot be shipped together, null if not found
     */
    private Boolean checkLabelCompatibility(String label1, String label2){
        for(CompatibleLabels c: labelCompatibilityGrid){                                            // Go through all CompatibleElements until you find the one you want.
            if(c.getLabel1().equals(label1) && c.getLabel2().equals(label2)){
                return c.getCompatible();
            }
        }
        return null;
    }

    /**
     * Setup the grid of compatible labels
     * @return An arraylist with compatibleLabels objects
     */
    private ArrayList<CompatibleLabels> setupLabelCompatibilityGrid(){
        ArrayList<CompatibleLabels> labelGrid = new ArrayList<CompatibleLabels>();
        labelGrid.add(new CompatibleLabels("a","a", true));                                         //TODO add more objects
        labelGrid.add(new CompatibleLabels("a", "f", false));
        return  labelGrid;
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
     * @return true if it succeeded, false otherwise.
     */
    public boolean addElement(int unID, String name){
        return db.addElement(unID, name);
    }

    /**
     * Calls on the database.removeElement method.
     *
     * @param elementID element to remove.
     * @return true if the element was removed, false otherwise.
     */
    public boolean removeElement(int elementID){
        return db.removeElement(elementID);
    }
}
