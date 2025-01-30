package astra.system;
import astra.task.DeadlineTask;
import astra.task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class ParserTest {

    @Test
    public void testCommand1(){
        assertEquals("aee", Parser.ParseCommand("       a     e     e   ", 2, true));
    }

    @Test
    public void testCommand2(){
        assertEquals("a  3   1", Parser.ParseCommand("a  3   1", 0, false));
    }

    @Test
    public void testTime3(){
        try {
            Parser.ParseTime("a  3   1");
        } catch (Exception e) {
            assertEquals("Invalid date time format", e.getMessage());
        }
    }

    @Test
    public void timeTest1(){
        try {
            assertEquals("01 December 1925" ,Parser.ParseTime("1925-12-01").displayTimeData());

        } catch (Exception e) {
            fail();
        }
    }

    //unaccepted format
    @Test
    public void timeTest2(){
        try {
            Parser.ParseTime("11-01-1025").displayTimeData();

        } catch (Exception e) {
            assertEquals("Invalid date time format", e.getMessage());
        }
    }
}
