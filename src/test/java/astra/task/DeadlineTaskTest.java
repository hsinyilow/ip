package astra.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import astra.system.AstraException;

public class DeadlineTaskTest {

    @Test
    public void parseTest1() {
        try {
            Task t1 = new DeadlineTask("deadline items and things /by 2019-05-01");
            assertEquals("[D][ ] items and things (by: 01 May 2019)", t1.displayTask());
        } catch (AstraException ignored) {
            System.out.println(ignored.getMessage());
        }
    }

    @Test
    public void test2() {
        try {
            Task t1 = new DeadlineTask("deadline /by 1134 /by 1441");
            fail();
        } catch (AstraException ae) {
            //parsing error
            assertEquals("Invalid Deadline astra.task.Task command", ae.getMessage());
        }
    }

    @Test
    public void test3() {
        try {
            Task t1 = new DeadlineTask("deadline /by 1134");
            fail();
        } catch (AstraException ae) {
            //has different ways to trigger error
            //assertEquals("Invalid Deadline astra.task.Task command", ae.getMessage());
            assertEquals("Invalid astra.task.Task description", ae.getMessage());

        }
    }
}
