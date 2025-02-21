package astra.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import astra.system.AstraException;


public class DeadlineTaskTest {

    /** Test Deadline Task constructor. */
    @Test
    public void createSuccessCase() {
        try {
            DeadlineTask t1 = DeadlineTask.createNewTask("deadline items and things /by 2019-05-01");
            assertEquals("[D][ ] items and things (by: 01 May 2019)", t1.displayTask());

            DeadlineTask t2 = DeadlineTask.createNewTask("deadline item /by 2020-01-03 23:59");
            assertEquals("[D][ ] item (by: 03 January 2020 11:59 pm)", t2.displayTask());

            DeadlineTask t3 = DeadlineTask.createNewTask("D | false | item | 2020-01-03T23:59 true");
            assertEquals(t2.displayTask(), t3.displayTask());

            DeadlineTask t4 = DeadlineTask.createNewTask("D | true | item | 2020-01-03T23:59 true");
            assertEquals(true, t4.isDone);

        } catch (AstraException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void loadCorruptedSave() {
        try {
            Task t1 = DeadlineTask.createNewTask("D | true | description | true |  ");
            fail();
        } catch (AstraException ae) {
            assertEquals("Save file is corrupted", ae.getMessage());
        }
    }

    @Test
    public void createWrongFormat() {
        try {
            Task t1 = DeadlineTask.createNewTask("deadline /by 2019-01-01 /by 2015-02-03");
            fail();
        } catch (AstraException ae) {
            //should have parsing error, not allowed more than 1 slash.
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
            //description should not be empty.
            assertEquals("Invalid task description", ae.getMessage());
        }
    }

    /** Test updating task. */
    @Test
    public void successUpdateCases() {
        try {
            DeadlineTask t1 = DeadlineTask.createNewTask("deadline items and things /by 2019-05-01");

            t1.updateDetails("desc some things here ");
            assertEquals("some things here", t1.description);

            t1.updateDetails("desc temp");
            t1.updateDetails("by 2020-01-01");
            assertEquals("[D][ ] temp (by: 01 January 2020)", t1.displayTask());

            t1.updateDetails("by 2020-01-01 23:59");
            assertEquals("[D][ ] temp (by: 01 January 2020 11:59 pm)", t1.displayTask());
        } catch (AstraException e) {
            fail();
        }
    }

    @Test
    public void invalidUpdateType() {
        try {
            DeadlineTask t1 = DeadlineTask.createNewTask("deadline items and things /by 2019-05-01");
            t1.updateDetails("new update here");
            fail();
        } catch (AstraException e) {
            assertEquals("this task detail type does not exist", e.getMessage());
        }

        //check non-class command.
        try {
            DeadlineTask t1 = DeadlineTask.createNewTask("deadline items and things /by 2019-05-01");
            t1.updateDetails("from a");
            fail();
        } catch (AstraException e) {
            assertEquals("this task detail type does not exist", e.getMessage());
        }

        try {
            DeadlineTask t1 = DeadlineTask.createNewTask("deadline items and things /by 2019-05-01");
            t1.updateDetails("to a");
            fail();
        } catch (AstraException e) {
            assertEquals("this task detail type does not exist", e.getMessage());
        }

        //check empty command.
        try {
            DeadlineTask t1 = DeadlineTask.createNewTask("deadline items and things /by 2019-05-01");
            t1.updateDetails("");
            fail();
        } catch (AstraException e) {
            assertEquals("this task detail type does not exist", e.getMessage());
        }
    }

    @Test
    public void invalidUpdateCommand() {
        try {
            DeadlineTask t1 = DeadlineTask.createNewTask("deadline items and things /by 2019-05-01");

            t1.updateDetails("desc       ");
        } catch (AstraException e) {
            assertEquals("new description cannot be empty", e.getMessage());
        }
    }

    @Test
    public void invalidSpacingCommand() {
        try {
            DeadlineTask t1 = DeadlineTask.createNewTask("deadline items and things /by 2019-05-01");
            t1.updateDetails("desclongstringthatdoesnothavemeaning");
            fail();
        } catch (AstraException e) {
            assertEquals("this task detail type does not exist", e.getMessage());
        }
    }
}
