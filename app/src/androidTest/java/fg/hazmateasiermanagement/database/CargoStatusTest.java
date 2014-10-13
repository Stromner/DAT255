package fg.hazmateasiermanagement.database;

import android.app.Application;
import android.database.Cursor;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import fg.hazmateasiermanagement.database.Database;

public class CargoStatusTest extends ApplicationTestCase<Application> {
    private Database database;
    private AccessDatabase accessDatabase;
    private CargoStatus cargoStatus;

    public CargoStatusTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        database = new Database(this.getContext());
    }

    @Override
    protected void tearDown() throws Exception {
        database.deleteTable();
        super.tearDown();
    }

    public void testPreconditions() {
        assertNotNull("database is null", database);
        assertNotNull("accessDatabase is null", accessDatabase);
        assertNotNull("cargoStatusTest is null", cargoStatus);
    }

}