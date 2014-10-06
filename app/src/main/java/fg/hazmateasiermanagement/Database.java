package fg.hazmateasiermanagement;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

import java.util.List;

/**
 * Contains all the UN elements that the application knows about and support for modifying the
 * database. Support saving the entire database locally or saving a smaller list of elements in a
 * list. Provided with a list of elements the database can check if the list would be okay to be
 * transported with each other.
 *
 * @author Johansson, Henrik
 * @author Stromner, David
 * @version 2014-09-30
 */

public class Database extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "hazmat_database.db";
    public static final String TABLE_NAME = "tableName";
    public static final String COLUMN_NAME_UN_ID = "un_id";
    public static final String COLUMN_NAME_UN_NAME = "un_name";

    public Database(Context context){
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME+"("+COLUMN_NAME_UN_ID+" integer PRIMARY KEY, "+COLUMN_NAME_UN_NAME+" text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME+"");
        onCreate(sqLiteDatabase);
    }

    /**
     * Create a new element and add it to the database. Trying to add duplicate elements to the list
     * will result in a return value of false. Duplicate in this context means if two elements
     * have the same UN_ID number.
     *
     * UN_ID which normally looks something like this: 'UN0023' should be added as '0023'
     *
     * @param UN_ID the UN identification number for the element.
     * @param NAME the proper name for the element.
     * @return true if it succeeded, false otherwise.
     */
    public Boolean addElement(int UN_ID, String NAME){
        try{
            SQLiteDatabase database = this.getWritableDatabase();
            database.execSQL("CREATE TABLE IF NOT EXISTS tableName("+COLUMN_NAME_UN_ID+" integer PRIMARY KEY,"+TABLE_NAME+" text);");
            database.execSQL("INSERT INTO tableName VALUES("+UN_ID+",'stuff');");
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * FOR TESTING PURPOSE ONLY, DO NOT USE IN APPLICATION CODE
     * Delete the entire table to ease in constructing tests for the database.
     */
    public void dropDatabase(){
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DROP TABLE "+TABLE_NAME+"");
    }

    /**
     * Removes an element from the database if elementID exists.
     *
     * UN_ID which normally looks something like this: 'UN0023' should be removed as '0023'
     *
     * @param elementID element to remove.
     * @return true if the element was removed, false otherwise.
     */
    public Boolean removeElement(int elementID){
        SQLiteDatabase database = this.getWritableDatabase();
        try {
            database.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME_UN_ID + " = " + elementID + "");
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Return an element that match elementID.
     *
     * UN_ID which normally looks something like this: 'UN0023' should be fetched as '0023'
     *
     * @param elementID element to fetch.
     * @return the element if it was found, null otherwise.
     */
    public Cursor getElement(int elementID){
        SQLiteDatabase database = this.getReadableDatabase();
        return database.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME_UN_ID + "= ?", new String[]{Integer.toString(elementID)});
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
