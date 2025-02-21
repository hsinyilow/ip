package astra.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import astra.system.AstraException;

public class EventTaskTest {
    /** Test Event Task constructor. */
    @Test
    public void createSuccessCase() {
        try {
            EventTask t1 = EventTask.createNewTask("event items /from 2020-01-02 /to 2022-02-02");
            assertEquals("[E][ ] items (from: 02 January 2020 to: 02 February 2022)", t1.displayTask());

            EventTask t2 = EventTask.createNewTask("event items /from 2020-01-02 10:10 /to 2022-02-02 23:59");
            assertEquals("[E][ ] items (from: 02 January 2020 10:10 am to: 02 February 2022 11:59 pm)",
                    t2.displayTask());

            EventTask t3 = EventTask.createNewTask("event items /from 2020-01-02 10:10 /to 2022-02-02");
            assertEquals("[E][ ] items (from: 02 January 2020 10:10 am to: 02 February 2022)",
                    t3.displayTask());

            EventTask t4 = EventTask.createNewTask("event items /from 2020-01-02 /to 2022-02-02 23:59");
            assertEquals("[E][ ] items (from: 02 January 2020 to: 02 February 2022 11:59 pm)",
                    t4.displayTask());

            String saveString = "E | true | item | 2020-01-03T23:59 false | 2020-01-03T23:59 false";
            EventTask t5 = EventTask.createNewTask(saveString);
            assertEquals(true, t5.isDone);

        } catch (AstraException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void createWrongFormat() {
        try {
            Task t1 = EventTask.createNewTask("event items /from 2020-01-02");
            fail();
        } catch (AstraException ae) {
            //missing command (/to).
            assertEquals("Invalid Event Task command", ae.getMessage());
        }

        try {
            //wrong command order.
            Task t1 = EventTask.createNewTask("event items /to 2020-01-02");
            fail();
        } catch (AstraException ae) {
            //missing command (/from).
            assertEquals("Invalid Event Task command", ae.getMessage());
        }

        try {
            //wrong command order.
            Task t1 = EventTask.createNewTask("event items /to 2020-01-02 /from 2020-01-02");
            fail();
        } catch (AstraException ae) {
            assertEquals("Invalid Event Task command", ae.getMessage());
        }
    }

    @Test
    public void createInvalidInformation() {
        try {
            EventTask t1 = EventTask.createNewTask("event /from 2020-01-01 /to 2020-01-01");
            fail();
        } catch (AstraException ae) {
            //description should not be empty.
            assertEquals("Invalid task description", ae.getMessage());
        }

        try {
            EventTask t1 = EventTask.createNewTask("event a /from  /to 2020-01-01");
            fail();
        } catch (AstraException ae) {
            //start should not be empty.
            assertEquals("Invalid event start", ae.getMessage());
        }

        try {
            EventTask t1 = EventTask.createNewTask("event a /from 2020-01-01 /to ");
            fail();
        } catch (AstraException ae) {
            //end should not be empty.
            assertEquals("Invalid event end", ae.getMessage());
        }
    }

    @Test
    public void checkInvalidDateTime() {
        try {
            EventTask t1 = EventTask.createNewTask("event a /from 2020-01-01 /to 01-02-2020");
            fail();
        } catch (AstraException ae) {
            //should be in time format.
            assertEquals("Invalid date time format", ae.getMessage());
        }
    }
}
