package fg.hazmateasiermanagement.database;

import android.app.Application;
import android.database.Cursor;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;

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
        Boolean result = accessDatabase.addElement(0, "test_element");
        assertTrue(result);
    }

    @SmallTest
    public void testAccessDatabase_addDuplicateElements(){
        accessDatabase.addElement(0, "test_element");
        Boolean result = accessDatabase.addElement(0, "test_element2"); // The '2' is on purpose
        assertFalse(result);
    }

    @SmallTest
    public void testAccessDatabase_removeElement(){
        accessDatabase.addElement(0, "test_element");
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
        accessDatabase.addElement(0,"test_element");
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
        accessDatabase.addElement(0, "test_element");
        assertNotNull(accessDatabase.getCompleteDatabase());
    }

    @SmallTest
    public void testGetCompleteDatabaseEmpty(){
        assertNull(accessDatabase.getCompleteDatabase());
    }

    @SmallTest
    public void testGoodTransportList(){
        String s[] = {"S1W1", "S2W2", "S4W1", "S100W100"};
        assertTrue(accessDatabase.checkCargoList(s));
    }

    @SmallTest
    public void testBadTransportList(){
        String s[] = {"S1W1", "S2W2wwww", "S4W1"};
        assertFalse(accessDatabase.checkCargoList(s));

        String s2[] = {"S1W1", "S2W-2", "S4W1"};
        assertFalse(accessDatabase.checkCargoList(s2));
    }
}