package astra.task;

import astra.system.AstraException;
import astra.system.Parser;

public class TodoTask extends Task {

    /**
     * Initializes a todo task object.
     * @param input the command with task data.
     * @throws AstraException If any of the task data is invalid or if the command is invalid.
     */
    public TodoTask(String input) throws AstraException {
        if (input.startsWith("T ")) {
            //load save
            String[] parseInput = Parser.parseSaveFile(input);
            if (parseInput.length == 1) {
                throw new AstraException("Invalid command");
            }
            this.description = parseInput[2];
            this.done = parseInput[1].equals("true");

        } else {
            //add task
            String result = Parser.parseCommand(input, 4, false);
            if (result.isEmpty()) {
                throw new AstraException("Invalid task description");
            }
            this.description = result;
        }
    }

    /**
     * Formats the data in save format.
     * @return Formatted data string.
     */
    @Override
    protected String saveString() {
        return String.format("T | %b | %s", done, description);
    }

    /**
     * Formats the data in display format.
     * @return Formatted data string.
     */
    @Override
    public String displayTask() {
        return String.format("[T][%s] %s", (done? "X" : " "), description);
    }
}
