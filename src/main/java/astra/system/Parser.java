package astra.system;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Parser {
    public static String ParseCommand(String command, int min, boolean removeSpace) {
        //command = command sent
        //min: min substring to cut
        if (command.length() <= min) {
            return "";
        }
        command = command.substring(min);
        if (removeSpace) {
            command = command.replace(" ", "");
        }
        return command.trim();
    }

    public static int ParseIntCommand(String command, int min) throws AstraException {
        command = Parser.ParseCommand(command, min, true);
        if (command.isEmpty()) {
            throw new AstraException("This is an invalid command");
        }

        try {
            return Integer.parseInt(command);

        } catch (NumberFormatException e) {
            throw new AstraException("This command requires a number");
        }
    }

    public static String[] ParseSaveFile(String input) {
        return input.split(" \\Q|\\E ");
    }

    public static TimeData ParseTime(String input) throws AstraException {
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

