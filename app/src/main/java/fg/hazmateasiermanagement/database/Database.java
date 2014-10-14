package fg.hazmateasiermanagement.database;

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
 * @version 2014-10-09
 */

public class Database extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 6;
    public static final String DATABASE_NAME = "hazmat_database.db";
    public static final String TABLE_NAME = "tableName";
    public static final String COLUMN_NAME_UN_ID = "un_id";
    public static final String COLUMN_NAME_UN_NAME = "un_name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_LABEL = "label";
    public static final String COLUMN_HAZMAT_IMAGE = "hazmat_image";
    public static final String COLUMN_NOT_COMPATIBLE = "not_compatible";

    public Database(Context context){
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME+"("+COLUMN_NAME_UN_ID+" INTEGER PRIMARY KEY,"+COLUMN_NAME_UN_NAME+" TEXT,"+COLUMN_DESCRIPTION+ " TEXT,"+COLUMN_LABEL+" TEXT,"+COLUMN_HAZMAT_IMAGE+" TEXT," +COLUMN_NOT_COMPATIBLE+ "TEXT );");
    }//int unNumber, String name, String description, String label, String hazmatImage, String notCompatible

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
     * @param DESCRIPTION describe the Element in detail.
     * @param LABEL used to show what material that can be shipped together.
     * @param HAZMAT_IMAGE file name of what image(if any) applies to this element).
     * @param NOT_COMPATIBLE shows which labels this element cannot be shipped with.
     * @return true if it succeeded, false otherwise.
     */
    boolean addElement(int UN_ID, String NAME, String DESCRIPTION, String LABEL, String HAZMAT_IMAGE, List<String> NOT_COMPATIBLE){
        try{
            String notCompatible = "";
            for (String s : NOT_COMPATIBLE) {
                notCompatible += s + ";";
            }
            notCompatible = notCompatible.substring(0, notCompatible.length() - 1);
            SQLiteDatabase database = this.getWritableDatabase();
            database.execSQL("INSERT INTO "+TABLE_NAME+" VALUES("+UN_ID+",'"+NAME+"','"+DESCRIPTION+"','"+LABEL+"','"+HAZMAT_IMAGE+"','"+notCompatible+"');");
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
    void deleteTable(){
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM "+TABLE_NAME);
        database.execSQL("VACUUM");
    }

    /**
     * Removes an element from the database if elementID exists.
     *
     * UN_ID which normally looks something like this: 'UN0023' should be removed as '0023'
     *
     * @param elementID element to remove.
     * @return true if the element was removed, false otherwise.
     */
    boolean removeElement(int elementID){
        SQLiteDatabase database = this.getWritableDatabase();
        try {
            database.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME_UN_ID + " = " + elementID);
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
    Cursor getElement(int elementID){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME, null, COLUMN_NAME_UN_ID + "=?", new String[]{String.valueOf(elementID)}, null, null, null, null);
        if(!cursor.moveToFirst()) {
            return null;
        }
        return cursor;
    }

    /**
     * @return Iterable cursor pointing to the first row in the database.
     * null if error occurs.
     */
    Cursor getCompleteDatabase(){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_NAME ,null);
        if(!cursor.moveToFirst()){
            return null;
        }
        return cursor;
    }
}
