import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IntegerSetTests {
    IntegerSet testSet;

    @Before
    public void setup(){
        testSet = new IntegerSet();
    }

    @Test
    public void testInsertNotThere(){
        //check number is not already in set
        assertEquals(testSet.size(), 0);
        assertFalse(testSet.contains(3));
        //insert a number
        testSet.insert(3);
        //check the number is in the set
        assertEquals(testSet.size(), 1);
        assertTrue(testSet.contains(3));
    }

    @Test
    public void testInsertAlreadyThere(){
        //check number is already in set
        assertEquals(testSet.size(), 0);
        assertFalse(testSet.contains(3));
        //insert a number
        testSet.insert(3);
        assertEquals(testSet.size() , 1);
        assertTrue(testSet.contains(3));

        //check the number is already in the set
        testSet.insert(3);
        assertTrue(testSet.contains(3));
        assertEquals(testSet.size() , 1);
        testSet.remove(3);
        assertFalse(testSet.contains(3));
        assertEquals(testSet.size() , 0);
    }
}