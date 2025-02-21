package astra.task;

import astra.gui.MainWindow;
import astra.system.AstraException;
import astra.system.DateTimeData;
import astra.system.Parser;
import astra.system.Ui;

/**
 * Creates and handle a deadline task.
 */
public class DeadlineTask extends Task {
    private DateTimeData deadline;

    /**
     * Initializes a deadline task object.
     *
     * @param description The description of deadline task.
     * @param isDone The completion state of deadline task.
     * @param deadline The deadline of deadline task.
     */
    private DeadlineTask(String description, boolean isDone, DateTimeData deadline) {
        this.description = description;
        this.isDone = isDone;
        this.deadline = deadline;
    }


    /**
     * Creates a new DeadlineTask with the given information.
     *
     * @param input The full command.
     * @return a new functional DeadlineTask object.
     * @throws AstraException If there are any invalid information or the save file is corrupted.
     */
    public static DeadlineTask createNewTask(String input) throws AstraException {
        assert input.startsWith("D") || input.startsWith("deadline")
                : "The deadline task object constructor should not have been called";

        if (input.startsWith("D ")) {
            /* handle input from save file*/
            String[] parseInput = Parser.parseSaveFile(input);

            if (parseInput.length != 4) {
                throw new AstraException("Save file is corrupted");
            }

            return new DeadlineTask(parseInput[2], parseInput[1].equals("true"), new DateTimeData(parseInput[3]));
        } else {
            /* handle input from user */
            String[] parseInput = input.split("/by");

            if (parseInput.length != 2) {
                throw new AstraException("Invalid Deadline task command");
            }

            String description = Parser.parseCommand(parseInput[0], 9, false);
            String deadline = Parser.parseCommand(parseInput[1], 0, false);

            if (description.isEmpty()) {
                throw new AstraException("Invalid task description");
            } else if (deadline.isEmpty()) {
                throw new AstraException("Invalid task deadline");
            }

            return new DeadlineTask(description, false, Parser.parseTime(deadline));
        }
    }


    /**
     * Updates the task with new information.
     *
     * @param input The possible changes made to the tasks.
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

        } else if (detailType.equals("by")) {
            String newDeadline = input.substring(commandBreak);
            newDeadline = Parser.parseCommand(newDeadline, 0, false);

            if (newDeadline.isEmpty()) {
                throw new AstraException("Invalid deadline");
            }
            deadline = Parser.parseTime(newDeadline);

        } else {
            throw new AstraException("this task detail type does not exist");
        }

        Ui.displayMessage("Updated:", displayTask());
        MainWindow.addMessage("Updated:", displayTask());
    }

    /**
     * Formats the data in display format.
     *
     * @return Formatted data string.
     */
    @Override
    public String displayTask() {
        return String.format("[D][%s] %s (by: %s)", (isDone ? "X" : " "), description, deadline.displayDateTime());
    }

    /**
     * Formats the data in save format.
     *
     * @return Formatted data string.
     */
    @Override
    protected String saveString() {
        return String.format("D | %b | %s | %s", isDone, description, deadline.saveDateTimeData());
    }
}
