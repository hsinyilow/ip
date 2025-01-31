package astra.task;

import astra.system.AstraException;
import astra.system.Parser;

public class TodoTask extends Task {

    /**
     * Constructor for Todo Task.
     *
     * @param input command and information of the task.
     * @throws AstraException If information or command is invalid.
     */
    public TodoTask(String input) throws AstraException {
        if (input.startsWith("T ")) {
            //load save
            String[] parseInput = Parser.ParseSaveFile(input);
            if (parseInput.length == 1) throw new AstraException("Invalid command");
            this.description = parseInput[2];
            this.done = parseInput[1].equals("true");

        } else {
            //add task
            String result = Parser.ParseCommand(input, 4, false);
            if (result.isEmpty()){
                throw new AstraException("Invalid task description");
            }
            this.description = result;
        }
    }

    /**
     * Display data.
     *
     * @return String of information for Ui.
     */
    @Override
    public String displayTask(){
        return String.format("[T][%s] %s", (done? "X" : " "), description);
    }

    /**
     * Save format of Todo Task.
     * This string is saved locally in a txt file.
     *
     * @return String of data that is being saved.
     */
    @Override
    protected String saveString(){
        return String.format("T | %b | %s", done, description);
    }

}
