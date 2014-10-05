package fg.hazmateasiermanagement;

import android.app.Application;
import android.database.Cursor;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import dalvik.annotation.TestTargetClass;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    private Database database;

    public ApplicationTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception{
        super.setUp();

        database = new Database(this.getContext());
    }

    @Override
    protected void tearDown() throws Exception{
        super.tearDown();
    }

    public void testPreconditions() {
        assertNotNull("database is null", database);
    }

    @SmallTest
    public void testDatabase_addElement(){
        Boolean result = database.addElement(0, "test_element");
        assertTrue(result);
    }

    @SmallTest
    public void testDatabase_addDuplicateElements(){
        database.addElement(0, "test_element");
        Boolean result = database.addElement(0, "test_element");
        assertFalse(result);
    }

    @SmallTest
    public void testDatabase_removeElement(){
        database.addElement(0, "test_element");
        Boolean result = database.removeElement(0);
        assertTrue(result);
    }

    @SmallTest
    public void testDatabase_removeNonExistingElement(){
        Boolean result = database.removeElement(0);
        assertTrue(result);
    }

    @SmallTest
     // This case is not properly written
     public void testDatabase_getElement(){
        database.addElement(0,"test_element");
        Cursor cursor = database.getElement(0);
        assertEquals(0, cursor.getColumnIndex("COLUMN_NAME_UN_ID"));
    }

    @SmallTest
    // This case is not properly written
    public void testDatabase_getNonExistingElement(){
        Cursor cursor = database.getElement(0);
        assertNull(cursor);
    }
}