package astra.task;

import astra.gui.MainWindow;
import astra.system.AstraException;
import astra.system.DateTimeData;
import astra.system.Parser;
import astra.system.Ui;

/**
 * Creates and handle an event task.
 */
public class EventTask extends Task {
    protected DateTimeData[] timings;

    /**
     * Initializes an event task object.
     *
     * @param description description of event task.
     * @param isDone completion state of event class.
     * @param timings start and end time of event task.
     */
    private EventTask(String description, boolean isDone, DateTimeData[] timings) {
        this.description = description;
        this.isDone = isDone;
        this.timings = timings;
    }

    /**
     * Creates a new EventTask with the given information.
     *
     * @param input The full command.
     * @return a new functional EventTask object.
     * @throws AstraException If there are any invalid information or the save file is corrupted.
     */
    public static EventTask createNewTask(String input) throws AstraException {
        assert input.startsWith("E") || input.startsWith("event")
                : "The event task object constructor should not have been called";

        if (input.startsWith("E ")) {
            return createTaskFromSaveFile(input);
        } else {
            return createTaskFromInput(input);
        }
    }

    /**
     * Creates an event task from save file.
     *
     * @param input The full command.
     * @return a new functional EventTask object.
     * @throws AstraException If save file is corrupted.
     */
    private static EventTask createTaskFromSaveFile(String input) throws AstraException {
        /* handle input from save file*/
        String[] parseInput = Parser.parseSaveFile(input);

        if (parseInput.length != 5) {
            throw new AstraException("Corrupted save file");
        }

        DateTimeData[] timings = {new DateTimeData(parseInput[3]), new DateTimeData(parseInput[4])};
        return new EventTask(parseInput[2], parseInput[1].equals("true"), timings);
    }

    /**
     * Creates an event task from user input.
     *
     * @param input The full command.
     * @return a new functional EventTask object.
     * @throws AstraException If there are any invalid information provided.
     */
    private static EventTask createTaskFromInput(String input) throws AstraException {
        String[] parseInput = input.split("/");
        boolean isInvalidCommand = !parseInput[1].startsWith("from") || !parseInput[2].startsWith("to");

        if (parseInput.length != 3 || isInvalidCommand) {
            throw new AstraException("Invalid Event Task command");
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

        DateTimeData[] timings = {Parser.parseTime(timeFrom), Parser.parseTime(timeTo)};
        return new EventTask(description, false, timings);
    }

    /**
     * Updates the task with new information.
     *
     * @param input possible changes made to the tasks.
     * @throws AstraException If the provided type of detail does not exist.
     */
    @Override
    void updateDetails(String input) throws AstraException {
        int commandBreak = input.indexOf(" ");
        if (commandBreak == -1) {
            throw new AstraException("this task detail type does not exist");
        }

        String detailType = input.substring(0, commandBreak);

        if (detailType.equals("desc")) {
            String newDescription = input.substring(commandBreak);
            newDescription = Parser.parseCommand(newDescription, 0, false);

            if (newDescription.isEmpty()) {
                throw new AstraException("new description cannot be empty");
            }
            description = newDescription;

        } else if (detailType.equals("from") || detailType.equals("to")) {
            String newTiming = input.substring(commandBreak);
            newTiming = Parser.parseCommand(newTiming, 0, false);

            if (newTiming.isEmpty()) {
                throw new AstraException("Invalid timing");
            }
            int timingType = detailType.equals("from") ? 0 : 1;
            timings[timingType] = Parser.parseTime(newTiming);

        } else {
            throw new AstraException("this task detail type does not exist");
        }

        Ui.displayMessage("Updated:", displayTask());
        MainWindow.addMessage("Updated:", displayTask());
    }

    /**
     * Formats the data in display format.
     * @return Formatted data string.
     */
    @Override
    public String displayTask() {
        return String.format("[E][%s] %s (from: %s to: %s)", (isDone ? "X" : " "), description,
                timings[0].displayDateTime(), timings[1].displayDateTime());
    }

    /**
     * Formats the data in save format.
     * @return Formatted data string.
     */
    @Override
    protected String saveString() {
        return String.format("E | %b | %s | %s | %s",
                isDone, description, timings[0].saveDateTimeData(), timings[1].saveDateTimeData());
    }
}
