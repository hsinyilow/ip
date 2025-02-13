package astra.task;

import astra.system.AstraException;
import astra.system.Parser;
import astra.system.TimeData;

/**
 * Is a deadline task.
 */
public class DeadlineTask extends Task {
    private TimeData deadline;

    /**
     * Initializes a deadline task object.
     * @param description description of deadline task.
     * @param isDone completion state of deadline task.
     * @param deadline deadline of deadline task.
     */
    private DeadlineTask(String description, boolean isDone, TimeData deadline) {
        this.description = description;
        this.isDone = isDone;
        this.deadline = deadline;
    }


    public static DeadlineTask createNewTask(String input) throws AstraException {
        if (input.startsWith("D ")) {
            /* handle input from save file*/
            String[] parseInput = Parser.parseSaveFile(input);

            if (parseInput.length != 4) {
                throw new AstraException("Save file is corrupted");
            }

            return new DeadlineTask(parseInput[2], parseInput[1].equals("true"), new TimeData(parseInput[3]));
        } else {
            /* handle input from user */
            String[] parseInput = input.split("/by");

            if (parseInput.length != 2) {
                throw new AstraException("Invalid Deadline astra.task.Task command");
            }

            String description = Parser.parseCommand(parseInput[0], 9, false);
            String deadline = Parser.parseCommand(parseInput[1], 0, false);

            if (description.isEmpty()) {
                throw new AstraException("Invalid astra.task.Task description");
            } else if (deadline.isEmpty()) {
                throw new AstraException("Invalid astra.task.Task deadline");
            }

            return new DeadlineTask(description, false, Parser.parseTime(deadline));
        }
    }

    /**
     * Formats the data in display format.
     * @return Formatted data string.
     */
    @Override
    public String displayTask() {
        return String.format("[D][%s] %s (by: %s)",
                (isDone ? "X" : " "), description, deadline.displayTimeData());
    }

    /**
     * Formats the data in save format.
     * @return Formatted data string.
     */
    @Override
    protected String saveString() {
        return String.format("D | %b | %s | %s", isDone, description, deadline.saveData());
    }
}
