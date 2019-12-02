import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WorkScheduleTests {

    WorkSchedule wsTest = new WorkSchedule(2);

    //Tests for domains in task 1A

    @Test //partition a1: starttime > endtime. nemployee in its input space.
    public void test_domain_a1(){
        wsTest.setRequiredNumber(5, 1, 0);
        WorkSchedule.Hour h0 = wsTest.readSchedule(0);
        WorkSchedule.Hour h1 = wsTest.readSchedule(1);
        int rn_h0 = h0.requiredNumber;
        int rn_h1 = h1.requiredNumber;

        assertEquals(0, rn_h0);
        assertEquals(0, rn_h1);

    }

    @Test // partition a2: starttime <= endtime and (starttime < 0 or endtime > size-1). nemployee in its input space.
    public void test_domain_a2(){
        //starttime outside of scheduled hours.
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {wsTest.setRequiredNumber(5, -1, 1);});
        //endtime outside of scheduled hours.
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {wsTest.setRequiredNumber(5, 0, 2);});
    }

    @Test //partition a3: starttime <= endtime and starttime >= 0 amd endtime < size.
          // nemployee < workingEmployees.length for every hour in starttime to endtime.
    public void test_domain_a3(){
        //addworkingperiod assumed to work as expected.
        wsTest.addWorkingPeriod("emp", 0, 0);
        wsTest.setRequiredNumber(0, 0, 0);
        WorkSchedule.Hour h0 = wsTest.readSchedule(0);
        int we_h0_length = h0.workingEmployees.length;
        int rn_h0 = h0.requiredNumber;

        assertEquals(0, we_h0_length);
        assertEquals(0, rn_h0);
    }

    @Test //partition a4: starttime <= endtime and starttime >=0 and endtime < size.
          // nemployee >= workingEmployees.length for every Hour in starttime to endtime.
    public void test_domain_a4(){
        wsTest.setRequiredNumber(1, 0, 0);
        WorkSchedule.Hour h0 = wsTest.readSchedule(0);
        int rn_h0 = h0.requiredNumber;
        assertEquals(1, rn_h0);
    }



    //Tests for domains in task 1B

    @Test //partition 1: requirednumber = workingemployees.length for each Hour.
    public void test_domain_b1(){
        assertEquals(-1, wsTest.nextIncomplete(0));
    }

    @Test //partition 2: requirednumber = workingemplyees.length for every hour but the last
          // where requirednumber > workingemployees.length
    public void test_domain_b2(){
        //sets requirednumber > workingemployees.length for last hour.
        wsTest.setRequiredNumber(1, 1, 1);

        assertEquals(1, wsTest.nextIncomplete(0));
    }



}
