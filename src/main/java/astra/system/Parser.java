package astra.system;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Handles parsing most commands.
 */
public class Parser {
    /**
     * Parses from full command to command data.
     *
     * @param command The command to be parsed.
     * @param minTrim Characters to trim, minimum character to consider as the command.
     * @param shouldRemoveSpace Whether space characters are allowed in parsed command.
     * @return Data from the command.
     */
    public static String parseCommand(String command, int minTrim, boolean shouldRemoveSpace) {
        if (command.length() <= minTrim) {
            return "";
        }

        command = command.substring(minTrim);
        assert !command.isEmpty() : "string should have content";

        if (shouldRemoveSpace) {
            command = command.replace(" ", "");
        }

        return command.trim();
    }

    /**
     * Parses commands that has integer data.
     *
     * @param command The command to be parsed.
     * @param minTrim Characters to trim, the minimum character to consider as the command.
     * @return Integer data from the command.
     * @throws AstraException If the command is invalid or the data is not an integer.
     */
    public static int parseIntCommand(String command, int minTrim) throws AstraException {
        command = Parser.parseCommand(command, minTrim, true);

        if (command.isEmpty()) {
            throw new AstraException("This is an invalid command");
        }

        try {
            return Integer.parseInt(command);
        } catch (NumberFormatException e) {
            throw new AstraException("This command requires a number");
        }
    }

    /**
     * Parses data from save file.
     *
     * @param input The raw data from the save file.
     * @return an array of data from the save file.
     */
    public static String[] parseSaveFile(String input) {
        return input.split(" \\Q|\\E ");
    }

    /**
     * Parses data related to date and time.
     *
     * @param input Data string of the specified format.
     * @return TimeData class containing all the necessary data.
     * @throws AstraException If string is in an invalid format.
     */
    public static DateTimeData parseTime(String input) throws AstraException {
        String[] parseInput = input.split(" ");

        try {
            LocalDate date = LocalDate.parse(parseInput[0]);
            LocalTime time = LocalTime.MIN;

            if (parseInput.length == 1) {
                return new DateTimeData(LocalDateTime.of(date, time), false);
            }

            //Creates and save the time object with the date.
            String[] splitTime = parseInput[1].split(":");
            int hours = Integer.parseInt(splitTime[0]);
            int minutes = Integer.parseInt(splitTime[1]);
            time = LocalTime.of(hours, minutes);

            return new DateTimeData(LocalDateTime.of(date, time), true);

        } catch (NumberFormatException e) {
            throw new AstraException("Time requires a number");
        } catch (Exception e) {
            throw new AstraException("Invalid date time format");
        }

    }
}

