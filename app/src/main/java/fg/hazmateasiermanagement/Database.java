package fg.hazmateasiermanagement;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "HazmatDatabase.db";

    /**
     *
     * @param context
     */
    public Database(Context context){
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }

    /**
     *
     * @param element
     * @return
     */
    public Boolean addElement(Element element){
        return false;
    }

    /**
     *
     * @param element
     * @return
     */
    public Boolean removeElement(Element element){
        return false;
    }

    /**
     *
     * @param position
     * @return
     */
    public Element getElement(int position){
        return null;
    }

    /**
     *
     * @param database
     * @return
     */
    public Boolean checkList(Database database){
        return false;
    }

    /**
     *
     * @return
     */
    public List getList(){
        return null;
    }
}
