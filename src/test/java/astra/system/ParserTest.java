package astra.system;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class ParserTest {

    @Test
    public void testCommand1(){
        assertEquals("aee", Parser.parseCommand("       a     e     e   ", 2, true));
    }

    @Test
    public void testCommand2(){
        assertEquals("a  3   1", Parser.parseCommand("a  3   1", 0, false));
    }

    @Test
    public void testTime3(){
        try {
            Parser.parseTime("a  3   1");
        } catch (Exception e) {
            assertEquals("Invalid date time format", e.getMessage());
        }
    }

    @Test
    public void timeTest1(){
        try {
            assertEquals("01 December 1925" ,Parser.parseTime("1925-12-01").displayTimeData());

        } catch (Exception e) {
            fail();
        }
    }

    //unaccepted format
    @Test
    public void timeTest2(){
        try {
            Parser.parseTime("11-01-1025").displayTimeData();

        } catch (Exception e) {
            assertEquals("Invalid date time format", e.getMessage());
        }
    }
}
