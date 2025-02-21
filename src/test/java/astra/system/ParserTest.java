package astra.system;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class ParserTest {

    /** Test parsing string command. */
    @Test
    public void testAdditionalSpacingAll() {
        assertEquals("aee",
                Parser.parseCommand("       a     e     e   ", 2, true));
    }

    @Test
    public void testAdditionalSpacingTrim() {
        assertEquals("a  3   1", Parser.parseCommand("a  3   1", 0, false));
        assertEquals("", Parser.parseCommand("           ", 0, false));
    }

    @Test
    public void testCommandTrim() {
        String result = Parser.parseCommand("deadline test   /by   date something", 8, false);
        assertEquals( "test   /by   date something", result);
    }


    /** Test parsing time command. */
    @Test
    public void testTimeParseInvalidFormat() {
        try {
            Parser.parseTime("01-01-2020");
        } catch (Exception e) {
            assertEquals("Invalid date time format", e.getMessage());
        }
    }


    @Test
    public void testTimeParseDateOnly() {
        try {
            assertEquals("11 January 2025", Parser.parseTime("2025-01-11").displayDateTime());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }

    }

    @Test
    public void testTimeParseDateAndTime() {
        try {
            assertEquals("11 January 2025 11:59 pm",
                    Parser.parseTime("2025-01-11 23:59").displayDateTime());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void testTimeParseSpacing() {
        try {
            Parser.parseTime("   2025-01-11    23:59   ");

        } catch (Exception e) {
            assertEquals("There is a formatting error, please try again!", e.getMessage());
        }
    }
}
