package astra.task;

import astra.system.AstraException;
import astra.system.Parser;
import astra.system.TimeData;

/**
 * Is an event task.
 */
public class EventTask extends Task {
    protected TimeData[] timings;

    /**
     * Initializes an event task object.
     * @param description description of event task.
     * @param isDone completion state of event class.
     * @param timings start and end time of event task.
     */
    private EventTask(String description, boolean isDone, TimeData[] timings) {
        this.description = description;
        this.isDone = isDone;
        this.timings = timings;
    }

    public static EventTask createNewTask(String input) throws AstraException {
        assert  input.startsWith("E") || input.startsWith("event")
                : "The event task object constructor should not have been called";

        if (input.startsWith("E ")) {
            /* handle input from save file*/
            String[] parseInput = Parser.parseSaveFile(input);

            if (parseInput.length != 5) {
                throw new AstraException("Corrupted save file");
            }

            TimeData[] timings = {new TimeData(parseInput[3]), new TimeData(parseInput[4])};
            return new EventTask(parseInput[2], parseInput[1].equals("true"), timings);

        } else {
            /* handle input from user */
            String[] parseInput = input.split("/");
            boolean isInvalidCommand = !parseInput[1].startsWith("from") || !parseInput[2].startsWith("to");

            if (parseInput.length != 3 || isInvalidCommand) {
                throw new AstraException("Invalid Event astra.task.Task command");
            }

            String description = Parser.parseCommand(parseInput[0], 5, false);
            String timeFrom = Parser.parseCommand(parseInput[1], 4, false);
            String timeTo = Parser.parseCommand(parseInput[2], 2, false);

            if (description.isEmpty()) {
                throw new AstraException("Invalid task description");
            } else if (timeFrom.isEmpty()) {
                throw new AstraException("Invalid event start");
            } else if (timeTo.isEmpty()) {
                throw new AstraException("Invalid event end");
            }

            TimeData[] timings = {Parser.parseTime(timeFrom), Parser.parseTime(timeTo)};
            return new EventTask(description, false, timings);
        }
    }

    /**
     * Formats the data in display format.
     * @return Formatted data string.
     */
    @Override
    public String displayTask() {
        return String.format("[E][%s] %s (from: %s to: %s)", (isDone ? "X" : " "), description,
                timings[0].displayTimeData(), timings[1].displayTimeData());
    }

    /**
     * Formats the data in save format.
     * @return Formatted data string.
     */
    @Override
    protected String saveString() {
        return String.format("E | %b | %s | %s | %s",
                isDone, description, timings[0].saveData(), timings[1].saveData());
    }
}
