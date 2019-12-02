import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SetTests {
    Set testSet = new Set();
    Set testSet2 = new Set();
    Set testSet3 = new Set();
    Set testSet4 = new Set();

    @Test
    public void testInsert(){
        //inserts 2,4,6,8
        for (int i = 2; i< 10; i+=2){
            testSet.insert(i);
        }
        testSet.insert(0);  //[0, 2, 4, 6, 8]
        testSet.insert(3);  //[0, 2, 3, 4, 6, 8]
        testSet.insert(6);  //[0, 2, 3, 4, 6, 8] - duplicate, no insert.

        assertEquals(0, testSet.toArray()[0]);
        assertEquals(3, testSet.toArray()[2]);
        assertEquals(6, testSet.toArray()[4]);
        assertEquals(6, testSet.toArray().length);

    }

    @Test
    public void testMember(){

        assertFalse(testSet.member(2));

        for (int i = 2; i< 10; i+=2){
            testSet.insert(i);
        }

        assertFalse(testSet.member(1));
        assertTrue(testSet.member(2));
        assertFalse(testSet.member(3));
        assertTrue(testSet.member(8));
    }

    @Test
    public void testIntersect1(){

        for (int i = 2; i< 10; i+=2){
            testSet.insert(i);
        }

        testSet2.insert(4);
        testSet.intersect(testSet2);
        assertFalse(testSet.member(2));
        assertTrue(testSet.member(4));
        assertFalse(testSet.member(6));
        assertFalse(testSet.member(8));


    }

    @Test
    public void testIntersect2(){
        for (int i = 2; i< 10; i+=2){
            testSet.insert(i);
        }

        testSet2.insert(4);
        testSet2.insert(5);
        testSet.intersect(testSet2);

        assertFalse(testSet.member(2));
        assertTrue(testSet.member(4));
        assertFalse(testSet.member(6));
        assertFalse(testSet.member(8));

    }

    @Test
    public void testDistinctClosed(){
        testSet.insert(1);
        testSet.insert(2);
        testSet.insert(3);

        assertTrue(testSet.distinctClosed((a, b) -> a + b));

        testSet2.insert(4);
        testSet2.insert(5);
        testSet2.insert(10);

        assertFalse(testSet2.distinctClosed((a, b) -> a + b));

        assertFalse(testSet3.distinctClosed((a, b) -> a + b));

        testSet4.insert(1);
        assertFalse(testSet4.distinctClosed((a, b) -> a + b));
    }
}
