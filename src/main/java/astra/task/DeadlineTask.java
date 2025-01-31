package astra.task;

import astra.system.AstraException;
import astra.system.Parser;
import astra.system.TimeData;

public class DeadlineTask extends Task{
    private TimeData deadline;

    /**
     * Constructor for Deadline Task.
     *
     * @param input command and information of the task.
     * @throws AstraException If information or command is invalid.
     */
    public DeadlineTask(String input) throws AstraException {

        if(input.startsWith("D ")) {
            String[] parseInput = Parser.ParseSaveFile(input);
            if (parseInput.length == 1) throw new AstraException("Invalid command");
            this.description = parseInput[2];
            this.done = parseInput[1].equals("true");
            this.deadline = new TimeData(parseInput[3]);
        } else {
            String[] parseInput = input.split("/by");
            if(parseInput.length != 2){
                throw new AstraException("Invalid Deadline astra.task.Task command");
            }

            String descriptionResult = Parser.ParseCommand(parseInput[0], 9, false);
            String deadlineResult = Parser.ParseCommand(parseInput[1], 0, false);

            if(descriptionResult.isEmpty()){
                throw new AstraException("Invalid astra.task.Task description");
            } else if (deadlineResult.isEmpty()) {
                throw new AstraException("Invalid astra.task.Task deadline");
            }

            this.description = descriptionResult;
            deadline = Parser.ParseTime(deadlineResult);
        }

    }

    /**
     * Display data.
     *
     * @return String of information for Ui.
     */
    @Override
    public String displayTask() {
        return String.format("[D][%s] %s (by: %s)", (done? "X" : " "), description, deadline.displayTimeData());
    }

    /**
     * Save format of Deadline Task.
     * This string is saved locally in a txt file.
     *
     * @return String of data that is being saved.
     */
    @Override
    protected String saveString(){
        return String.format("D | %b | %s | %s", done, description, deadline.saveData());
    }
}
