public class DeadlineTask extends Task{
    private TimeData deadline;

    public DeadlineTask(String input) throws AstraException{

        if(input.startsWith("D ")) {
            String[] parseInput = Parser.ParseSaveFile(input);
            this.description = parseInput[2];
            this.done = parseInput[1].equals("true");
            this.deadline = new TimeData(parseInput[3]);
        } else {
            String[] parseInput = input.split("/by");
            if(parseInput.length != 2){
                throw new AstraException("Invalid Deadline Task command");
            }

            String descriptionResult = Parser.ParseCommand(parseInput[0], 9, false);
            String deadlineResult = Parser.ParseCommand(parseInput[1], 0, false);

            if(descriptionResult.isEmpty()){
                throw new AstraException("Invalid Task description");
            } else if (deadlineResult.isEmpty()) {
                throw new AstraException("Invalid Task deadline");
            }

            this.description = descriptionResult;
            deadline = Parser.ParseTime(deadlineResult);
        }

    }

    @Override
    public String displayTask() {
        return String.format("[D][%s] %s (by: %s)", (done? "X" : " "), description, deadline.displayTimeData());
    }

    @Override
    protected String saveString(){
        return String.format("D | %b | %s | %s", done, description, deadline.saveData());
    }
}
