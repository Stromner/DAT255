package fg.hazmateasiermanagement.database;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import java.util.LinkedList;
import java.util.List;

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
    public void testDatabaseSeed(){
        //Seed seed = Seed.getInstance();
        assertNull(database.getElement(4));
        //seed.seedElements(database);
        assertNotNull(database.getElement(4));

        AccessDatabase aDB = new AccessDatabase(database);
        Element e = new Element(4, "AMMONIUM PICTRATE", "Dry or wetted with less than 10% water, by mass", 3500, "2.1", "default","1;1.4;1.5;1.6;4.1;5.2");
        // Check that all fields are the same
        assertEquals(e.getUNNumber(), aDB.getElement(4).getUNNumber());
        assertEquals(e.getName(), aDB.getElement(4).getName());
        assertEquals(e.getDescription(), aDB.getElement(4).getDescription());
        assertEquals(e.getLabel(), aDB.getElement(4).getLabel());
        assertEquals(e.getHazmatImage(), aDB.getElement(4).getHazmatImage());
        assertEquals(e.getNotCompatible(), aDB.getElement(4).getNotCompatible());

        // Check that all elements that was added in seed do exist.
        assertNotNull(aDB.getElement(4));
        assertNotNull(aDB.getElement(1286));
        assertNotNull(aDB.getElement(1541));
        assertNotNull(aDB.getElement(1761));
        assertNotNull(aDB.getElement(1474));
        assertNotNull(aDB.getElement(2909));
        assertNotNull(aDB.getElement(1203));
        assertNotNull(aDB.getElement(1204));
        assertNotNull(aDB.getElement(1210));
        assertNotNull(aDB.getElement(2923));
        assertNotNull(aDB.getElement(3077));

        // There should not exist an element with unId 999 in the database
        assertNull(aDB.getElement(999));
    }

    @SmallTest
    public void testAddElement(){
        List<String> list = new LinkedList<String>();
        list.add("test_not_compatible");
        Boolean result = accessDatabase.addElement(0, "test_element", "test_description", 0, "test_label", "test_image_path", list);
        assertTrue(result);
    }

    @SmallTest
    public void testAddDuplicateElements(){
        List<String> list = new LinkedList<String>();
        list.add("test_not_compatible");
        accessDatabase.addElement(0, "test_element", "test_description",3500, "test_label", "test_image_path", list);
        Boolean result = accessDatabase.addElement(0, "test_element2", "test_description",3500, "test_label", "test_image_path", list); // The '2' is on purpose
        assertFalse(result);
    }

    @SmallTest
    public void testRemoveElement(){
        List<String> list = new LinkedList<String>();
        list.add("test_not_compatible");
        accessDatabase.addElement(0, "test_element", "test_description",3500, "test_label", "test_image_path", list);
        Boolean result = accessDatabase.removeElement(0);
        assertTrue(result);
    }

    @SmallTest
    public void testRemoveNonExistingElement(){
        Boolean result = accessDatabase.removeElement(0);
        assertTrue(result);
    }

    @SmallTest
    public void testGetElement(){
        List<String> list = new LinkedList<String>();
        list.add("test_not_compatible;notCop");
        accessDatabase.addElement(0, "test_element", "test_description",3500, "test_label", "test_image_path", list);
        Element element = accessDatabase.getElement(0);
        assertEquals(element.getUNNumber(), 0);
    }

    @SmallTest
    public void testGetNonExistingElement(){
        Element element = accessDatabase.getElement(0);
        assertNull(element);
    }

    @SmallTest
    public void testGetCompleteDatabaseNotEmpty() {
        List<String> list = new LinkedList<String>();
        list.add("test_not_compatible");
        accessDatabase.addElement(0, "test_element", "test_description",3500, "test_label", "test_image_path", list);
        assertNotNull(accessDatabase.getCompleteDatabase());
    }

    @SmallTest
    public void testGetCompleteDatabaseEmpty(){
        assertNull(accessDatabase.getCompleteDatabase());
    }
}