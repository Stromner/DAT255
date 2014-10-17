package fg.hazmateasiermanagement.database;

import android.app.Application;
import android.database.Cursor;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import java.util.LinkedList;
import java.util.List;

public class DatabaseTest extends ApplicationTestCase<Application> {
    private Database database;

    public DatabaseTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception{
        super.setUp();
        database = new Database(this.getContext());
    }

    @Override
    protected void tearDown() throws Exception{
        database.deleteTable();
        super.tearDown();
    }

    public void testPreconditions() {
        assertNotNull("database is null", database);
    }

    @SmallTest
    public void testAddElement(){
        List<String> list = new LinkedList<String>();
        list.add("test_not_compatible");
        boolean result = database.addElement(0, "test_element", "test_description",3500, "test_label", "test_image_path", list);
        assertTrue(result);
    }

    @SmallTest
    public void testAddDuplicateElements(){
        List<String> list = new LinkedList<String>();
        list.add("test_not_compatible");
        database.addElement(0, "test_element", "test_description",3500, "test_label", "test_image_path", list);
        boolean result = database.addElement(0, "test_element2", "test_description",3500, "test_label", "test_image_path", list); // The '2' is on purpose
        assertFalse(result);
    }

    @SmallTest
    public void testRemoveElement(){
        List<String> list = new LinkedList<String>();
        list.add("test_not_compatible");
        database.addElement(0, "test_element", "test_description",3500, "test_label", "test_image_path", list);
        Boolean result = database.removeElement(0);
        assertTrue(result);
    }

    @SmallTest
    public void testRemoveNonExistingElement(){
        Boolean result = database.removeElement(0);
        assertTrue(result);
    }

    @SmallTest
     public void testGetElement(){
        List<String> list = new LinkedList<String>();
        list.add("test_not_compatible");
        database.addElement(0, "test_element", "test_description", 3500, "test_label", "test_image_path", list);
        Cursor cursor = database.getElement(0);
        assertEquals(cursor.getInt(0), 0);
    }

    @SmallTest
    public void testGetNonExistingElement(){
        assertNull(database.getElement(0));
    }

    @SmallTest
    public void testGetCompleteDatabase(){
        List<String> list = new LinkedList<String>();
        list.add("test_not_compatible");
        database.addElement(0, "test_element", "test_description",3500, "test_label", "test_image_path", list);
        assertNotNull(database.getCompleteDatabase());
    }

    @SmallTest
    public void testGetNonExistingCompleteDatabase(){
        assertNull(database.getCompleteDatabase());
    }
}