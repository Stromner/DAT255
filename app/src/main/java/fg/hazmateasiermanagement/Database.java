package fg.hazmateasiermanagement;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

import java.util.List;
import fg.hazmateasiermanagement.Element;

/**
 * Contains all the UN elements that the application knows about and support for modifying the
 * database. Support saving the entire database locally or saving a smaller list of elements in a
 * list. Provided with a list of elements the database can check if the list would be okay to be
 * transported with each other.
 *
 * @author Johansson, Henrik
 * @author Stromner, David
 * @version 2014-10-05
 */

public class Database extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "hazmat_database.db";
    public static final String TABLE_NAME = "tableName";
    public static final String COLUMN_NAME_UN_ID = "un_id";
    public static final String COLUMN_NAME_UN_NAME = "un_name";

    /**
     *
     * @param context
     */
    public Database(Context context){
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String onCreate_S = "CREATE TABLE "+TABLE_NAME+"("+COLUMN_NAME_UN_ID+" integer PRIMARY KEY, "+COLUMN_NAME_UN_NAME+" text);";
        sqLiteDatabase.execSQL(onCreate_S);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME+"");
        onCreate(sqLiteDatabase);
    }

    /**
     *
     * @param UN_ID
     * @param NAME
     * @return
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

    public void dropDatabase(){
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DROP TABLE "+TABLE_NAME+"");
    }

    /**
     *
     * @param element_id
     * @return
     */
    public Boolean removeElement(int element_id){
        SQLiteDatabase database = this.getWritableDatabase();
        //int result = database.delete("table", COLUMN_NAME_UN_ID + "=" + element_id, null);
        try {
            database.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME_UN_ID + " = " + element_id + "");
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
            return true;
    }

    /**
     *
     * @param element_id
     * @return
     */
    public Cursor getElement(int element_id){
        SQLiteDatabase database = this.getReadableDatabase();
        return database.rawQuery("SELECT * FROM table WHERE"+COLUMN_NAME_UN_ID+"="+element_id,null);
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
