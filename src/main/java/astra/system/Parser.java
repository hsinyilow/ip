package astra.system;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Parser {

    /**
     * Returns parsed String command.
     *
     * @param command command string.
     * @param min command name to cut.
     * @param removeSpace to remove space character from command.
     * @return String command.
     */
    public static String ParseCommand(String command, int min, boolean removeSpace){
        //command = command sent
        //min: min substring to cut
        if (command.length() <= min) return "";
        command = command.substring(min);
        if (removeSpace) command = command.replace(" ", "");
        return command.trim();
    }

    /**
     * Returns parsed int command.
     *
     * @param command command string.
     * @param min command name to cut.
     * @return int command.
     * @throws AstraException If the command contains a char instead of number.
     */
    public static int ParseIntCommand(String command, int min) throws AstraException{
        command = Parser.ParseCommand(command, min, true);
        if (command.isEmpty()) {
            throw new AstraException("This is an invalid command");
        }

        try{
            return Integer.parseInt(command);

        } catch (NumberFormatException e){
            throw new AstraException("This command requires a number");
        }
    }

    /**
     * Returns data from save file
     *
     * @param input String command.
     * @return array of data from command.
     */
    public static String[] ParseSaveFile(String input){

        return input.split(" \\Q|\\E ");
    }

    /**
     * Returns date and time
     *
     * @param input date and optional time.
     * @return TimeData.
     * @throws AstraException If given date or time is in wrong format.
     */
    public static TimeData ParseTime(String input) throws AstraException{
        String[] parseInput = input.split(" ");

        try {
            LocalDate date = LocalDate.parse(parseInput[0]);
            LocalTime time = LocalTime.MIN;
            if (parseInput.length != 1){
                time = LocalTime.of(Integer.parseInt(parseInput[1].substring(0,2)),
                        Integer.parseInt(parseInput[1].substring(2)));

                return new TimeData(LocalDateTime.of(date, time), true);
            }
            return new TimeData(LocalDateTime.of(date, time), false);
        } catch (Exception e) {
            throw new AstraException("Invalid date time format");
        }


    }
}

