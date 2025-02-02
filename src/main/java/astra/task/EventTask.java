package astra.task;

import astra.system.AstraException;
import astra.system.Parser;
import astra.system.TimeData;

public class EventTask extends Task {
    protected TimeData[] timings = new TimeData[2];

    /**
     * Initializes an event task object.
     * @param input the command with task data.
     * @throws AstraException If any of the task data is invalid or if the command is invalid.
     */
    public EventTask(String input) throws AstraException {
        if(input.startsWith("E ")) {
            String[] parseInput = Parser.ParseSaveFile(input);
            if (parseInput.length == 1) {
                throw new AstraException("Invalid command");
            }
            this.done = parseInput[1].equals("true");
            this.description = parseInput[2];

            timings[0]= new TimeData(parseInput[3]);
            timings[1] = new TimeData(parseInput[4]);
        } else {
            String[] parseInput = input.split("/");
            if(parseInput.length != 3 ||
                    !parseInput[1].startsWith("from") || !parseInput[2].startsWith("to")) {
                throw new AstraException("Invalid Event astra.task.Task command");
            }

            String descriptionResult = Parser.ParseCommand(parseInput[0], 5, false);
            String[] timingResult = {Parser.ParseCommand(parseInput[1], 4, false),
                    Parser.ParseCommand(parseInput[2], 2, false)};

            if (descriptionResult.isEmpty()) {
                throw new AstraException("Invalid task description");
            } else if (timingResult[0].isEmpty()) {
                throw new AstraException("Invalid event start");
            } else if (timingResult[1].isEmpty()) {
                throw new AstraException("Invalid event end");
            }

            this.description = descriptionResult;

            for (int i = 0; i < 2; i++) {
                timings[i] = Parser.ParseTime(timingResult[i]);
            }
        }

    }

    /**
     * Formats the data in display format.
     * @return Formatted data string.
     */
    @Override
    public String displayTask() {
        return String.format("[E][%s] %s (from: %s to: %s)", (done? "X" : " "), description,
                timings[0].displayTimeData(), timings[1].displayTimeData());
    }

    /**
     * Formats the data in save format.
     * @return Formatted data string.
     */
    @Override
    protected String saveString() {
        return String.format("E | %b | %s | %s | %s", done, description, timings[0].saveData(), timings[1].saveData());
    }
}
