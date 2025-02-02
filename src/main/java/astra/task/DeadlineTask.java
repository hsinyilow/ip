package astra.task;

import astra.system.AstraException;
import astra.system.Parser;
import astra.system.TimeData;

public class DeadlineTask extends Task {
    private TimeData deadline;

    /**
     * Initializes a deadline task object.
     * @param input the command with task data.
     * @throws AstraException If any of the task data is invalid or if the command is invalid.
     */
    public DeadlineTask(String input) throws AstraException {

        if (input.startsWith("D ")) {
            String[] parseInput = Parser.parseSaveFile(input);
            if (parseInput.length == 1) {
                throw new AstraException("Invalid command");
            }
            this.description = parseInput[2];
            this.done = parseInput[1].equals("true");
            this.deadline = new TimeData(parseInput[3]);
        } else {
            String[] parseInput = input.split("/by");
            if (parseInput.length != 2) {
                throw new AstraException("Invalid Deadline astra.task.Task command");
            }

            String descriptionResult = Parser.parseCommand(parseInput[0], 9, false);
            String deadlineResult = Parser.parseCommand(parseInput[1], 0, false);

            if (descriptionResult.isEmpty()) {
                throw new AstraException("Invalid astra.task.Task description");
            } else if (deadlineResult.isEmpty()) {
                throw new AstraException("Invalid astra.task.Task deadline");
            }

            this.description = descriptionResult;
            deadline = Parser.parseTime(deadlineResult);
        }

    }

    /**
     * Formats the data in display format.
     * @return Formatted data string.
     */
    @Override
    public String displayTask() {
        return String.format("[D][%s] %s (by: %s)", (done? "X" : " "), description, deadline.displayTimeData());
    }

    /**
     * Formats the data in save format.
     * @return Formatted data string.
     */
    @Override
    protected String saveString() {
        return String.format("D | %b | %s | %s", done, description, deadline.saveData());
    }
}
