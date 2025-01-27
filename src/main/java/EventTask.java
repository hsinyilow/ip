public class EventTask extends Task{
    protected String start;
    protected String end;
    public EventTask(String input) throws AstraException{
        super();
        String[] parseInput = input.split("/");
        if(parseInput.length != 3 ||
           !parseInput[1].startsWith("from") || !parseInput[2].startsWith("to")){
            throw new AstraException("Invalid Event Task command");
        }

        String descriptionResult = commandCheck(parseInput[0], 5);
        String startResult = commandCheck(parseInput[1], 4);
        String endResult = commandCheck(parseInput[2], 2);

        if(descriptionResult.isEmpty()){
            throw new AstraException("Invalid task description");
        } else if (startResult.isEmpty()) {
            throw new AstraException("Invalid event start");
        } else if (endResult.isEmpty()){
            throw new AstraException("Invalid event end");
        }

        this.description = descriptionResult;
        this.start = startResult;
        this.end = endResult;
    }

    @Override
    public String displayTask() {
        return String.format("[E][%s] %s (from: %s to: %s)", (done? "X" : " "), description, start, end);
    }
}
