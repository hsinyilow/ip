package astra.task;

import astra.system.AstraException;
import astra.system.Parser;

/**
 * Is a todo task.
 */
public class TodoTask extends Task {

    /**
     * Initializes a todo task object.
     * @param description description of the todo task.
     * @param isDone completion status of the todo task.
     */
    private TodoTask(String description, boolean isDone){
        this.description = description;
        this.isDone = isDone;
    }


    public static TodoTask createNewTask(String input) throws AstraException {
        assert  input.startsWith("T") || input.startsWith("todo")
                : "The todo task object constructor should not have been called";

        if (input.startsWith("T ")) {
            /* handle input from save file*/
            String[] parseInput = Parser.parseSaveFile(input);

            if (parseInput.length != 3) {
                throw new AstraException("Save file is corrupted");
            }

            return new TodoTask(parseInput[2], parseInput[1].equals("true"));

        } else {
            /* handle input from user */
            String description = Parser.parseCommand(input, 4, false);

            if (description.isEmpty()) {
                throw new AstraException("Invalid task description");
            }
            return new TodoTask(description, false);
        }
    }

    /**
     * Formats the data in save format.
     * @return Formatted data string.
     */
    @Override
    protected String saveString() {
        return String.format("T | %b | %s", isDone, description);
    }

    /**
     * Formats the data in display format.
     * @return Formatted data string.
     */
    @Override
    public String displayTask() {
        return String.format("[T][%s] %s", (isDone ? "X" : " "), description);
    }
}
