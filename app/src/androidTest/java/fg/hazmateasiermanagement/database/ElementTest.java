package fg.hazmateasiermanagement.database;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import fg.hazmateasiermanagement.Element;

/**
 * Created by Henrik on 2014-10-14.
 */
public class ElementTest extends ApplicationTestCase<Application> {

    public ElementTest(){
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception{
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception{
        super.tearDown();
    }

    public void testPreconditions() {

    }

    @SmallTest
    public void testCloneElement(){
        Element element,clonedElement;
        element = new Element(0, "name1", "description", "A", "default","B;C;D");
        try{
            clonedElement = (Element) element.clone();

            // Compares all fields in the Elements with each field in clonedElement
            assertEquals(element.getUNNumber(), clonedElement.getUNNumber());
            assertEquals(element.getName(), clonedElement.getName());
            assertEquals(element.getDescription(), clonedElement.getDescription());
            assertEquals(element.getLabel(),clonedElement.getLabel());
            assertEquals(element.getHazmatImage(), clonedElement.getHazmatImage());

            // Testing with changed weight
            assertEquals(element.getWeight(),clonedElement.getWeight());
            element.setWeight(4);
            clonedElement = (Element) element.clone();
            assertEquals(element.getWeight(), clonedElement.getWeight());

            // Testing that there has been a deep copy of the array notCompatible
            assertEquals(element.getNotCompatible(), clonedElement.getNotCompatible());
            element.setNotCompatible("B;C");
            assertNotSame(element.getNotCompatible(), clonedElement.getNotCompatible());
            element.setNotCompatible("B;C;D");
            assertEquals(element.getNotCompatible(), clonedElement.getNotCompatible());

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @SmallTest
    public void testElementCompatible(){
        Element element1,element2,element3;
        element1 = new Element(0, "name1", "description", "A", "default","B;C;D");
        element2 = new Element(1, "name2", "description2", "A", "default","B;C;D");
        element3 = new Element(2, "name3", "description", "B", "default","A;C;D");

        // Can you package an element with itself?
        assertTrue(element1.isCompatible(element1));
        // Can you package two elements that should be able to package together
        assertTrue(element1.isCompatible(element2));
        //Can you package two uncompatible elements?
        assertFalse(element1.isCompatible(element3));

    }

}
