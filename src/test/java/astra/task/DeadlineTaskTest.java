package astra.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import astra.system.AstraException;


public class DeadlineTaskTest {

    /**Static Constructor testing: function is createNewTask */
    @Test
    public void createSuccessCase() {
        try {
            DeadlineTask t1 = DeadlineTask.createNewTask("deadline items and things /by 2019-05-01");
            assertEquals("[D][ ] items and things (by: 01 May 2019)", t1.displayTask());
        } catch (AstraException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void createWrongFormat() {
        try {
            Task t1 = DeadlineTask.createNewTask("deadline /by 2019-01-01 /by 2015-02-03");
            fail();
        } catch (AstraException ae) {
            //should have parsing error
            assertEquals("Invalid Deadline task command", ae.getMessage());
        }
    }

    @Test
    public void createOddSpacing() {
        try {
            Task t1 = DeadlineTask.createNewTask("deadline to do   this    /by    2020-01-01");
            assertEquals("[D][ ] to do   this (by: 01 January 2020)", t1.displayTask());
        } catch (AstraException ae) {
            fail();
        }
    }

    @Test
    public void createEmptyDescription() {
        try {
            Task t1 = DeadlineTask.createNewTask("deadline /by 2020-01-01");
            fail();
        } catch (AstraException ae) {
            //description should not be empty
            assertEquals("Invalid task description", ae.getMessage());
        }
    }

    @Test
    public void createCheckAssertionWorking() {
        try {
            Task t1 = DeadlineTask.createNewTask("dead test /by 2015-01-05");
            fail();
        } catch (AstraException ae) {
            //description should not be empty
            assertEquals("The deadline task object constructor should not have been called", ae.getMessage());
        }
    }
}
