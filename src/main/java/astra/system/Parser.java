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
     * @param trimCommandAmt Characters to trim, i.e. the length of main command keyword.
     * @param shouldRemoveSpace Whether space characters are allowed in parsed command.
     * @return Data from the command.
     */
    public static String parseCommand(String command, int trimCommandAmt, boolean shouldRemoveSpace) {
        if (command.length() <= trimCommandAmt) {
            return "";
        }

        command = command.substring(trimCommandAmt);
        command = command.trim();

        if (shouldRemoveSpace) {
            assert !command.isEmpty() : "string should have content";
            command = command.replace(" ", "");
        }

        return command;
    }

    /**
     * Parses commands that has integer data.
     *
     * @param command The command to be parsed.
     * @param trimCommandAmt Characters to trim, the minimum character to consider as the command.
     * @return Integer data from the command.
     * @throws AstraException If the command is invalid or the data is not an integer.
     */
    public static int parseIntCommand(String command, int trimCommandAmt) throws AstraException {
        command = Parser.parseCommand(command, trimCommandAmt, true);

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

        if (parseInput.length > 2) {
            throw new AstraException("There is a formatting error, please try again!");
        }

        try {
            LocalDate date = LocalDate.parse(parseInput[0]);
            LocalTime time = LocalTime.MIN;

            if (parseInput.length == 1) {
                return new DateTimeData(LocalDateTime.of(date, time), false);
            }

            //Creates and save the time object with the date.
            //assert length is 5 because time format "23:59" has 5 characters.
            assert parseInput[1].length() == 5 : "Time format is wrong";
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

