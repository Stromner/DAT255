package fg.hazmateasiermanagement.database;

import android.app.Application;
import android.database.Cursor;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;

import java.util.LinkedList;
import java.util.List;

import fg.hazmateasiermanagement.database.Database;
import fg.hazmateasiermanagement.database.AccessDatabase;
import fg.hazmateasiermanagement.Element;

public class AccessDatabaseTest extends ApplicationTestCase<Application> {
    private Database database;
    private AccessDatabase accessDatabase;

    public AccessDatabaseTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception{
        super.setUp();
        database = new Database(this.getContext());
        accessDatabase = new AccessDatabase(database);
    }

    @Override
    protected void tearDown() throws Exception{
        database.deleteTable();
        super.tearDown();
    }

    public void testPreconditions() {
        assertNotNull("accessDatabase is null", accessDatabase);
        assertNotNull("database is null", database);
    }

    @SmallTest
    public void testAccessDatabase_addElement(){
        List<String> list = new LinkedList<String>();
        list.add("test_not_compatible");
        Boolean result = accessDatabase.addElement(0, "test_element", "test_description", "test_label", "test_image_path", list);
        assertTrue(result);
    }

    @SmallTest
    public void testAccessDatabase_addDuplicateElements(){
        List<String> list = new LinkedList<String>();
        list.add("test_not_compatible");
        accessDatabase.addElement(0, "test_element", "test_description", "test_label", "test_image_path", list);
        Boolean result = accessDatabase.addElement(0, "test_element2", "test_description", "test_label", "test_image_path", list); // The '2' is on purpose
        assertFalse(result);
    }

    @SmallTest
    public void testAccessDatabase_removeElement(){
        List<String> list = new LinkedList<String>();
        list.add("test_not_compatible");
        accessDatabase.addElement(0, "test_element", "test_description", "test_label", "test_image_path", list);
        Boolean result = accessDatabase.removeElement(0);
        assertTrue(result);
    }

    @SmallTest
    public void testAccessDatabase_removeNonExistingElement(){
        Boolean result = accessDatabase.removeElement(0);
        assertTrue(result);
    }

    @SmallTest
    public void testAccessDatabase_getElement(){
        List<String> list = new LinkedList<String>();
        list.add("test_not_compatible");
        accessDatabase.addElement(0, "test_element", "test_description", "test_label", "test_image_path", list);
        Element element = accessDatabase.getElement(0);
        assertEquals(element.getUNNumber(), 0);
    }

    @SmallTest
    public void testAccessDatabase_getNonExistingElement(){
        Element element = accessDatabase.getElement(0);
        assertNull(element);
    }

    @SmallTest
    public void testGetCompleteDatabaseNotEmpty() {
        List<String> list = new LinkedList<String>();
        list.add("test_not_compatible");
        accessDatabase.addElement(0, "test_element", "test_description", "test_label", "test_image_path", list);
        assertNotNull(accessDatabase.getCompleteDatabase());
    }

    @SmallTest
    public void testGetCompleteDatabaseEmpty(){
        assertNull(accessDatabase.getCompleteDatabase());
    }
}