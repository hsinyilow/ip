public class DeadlineTask extends Task{
    protected String deadline;
    public DeadlineTask(String input) throws AstraException{

        if(input.startsWith("D ")) {
            String[] parseInput = input.split(" \\Q|\\E ");
            this.description = parseInput[2];
            this.done = parseInput[1].equals("true");
            this.deadline = parseInput[3];
        } else {
            String[] parseInput = input.split("/");
            if(parseInput.length != 2 || !parseInput[1].startsWith("by")){
                throw new AstraException("Invalid Deadline Task command");
            }

            String descriptionResult = commandCheck(parseInput[0], 9);
            String deadlineResult = commandCheck(parseInput[1], 3);

            if(descriptionResult.isEmpty()){
                throw new AstraException("Invalid Task description");
            } else if (deadlineResult.isEmpty()) {
                throw new AstraException("Invalid Task deadline");
            }

            this.description = descriptionResult;
            this.deadline = deadlineResult;
        }


    }

    @Override
    public String displayTask() {
        return String.format("[D][%s] %s (by: %s)", (done? "X" : " "), description, deadline);
    }

    @Override
    protected String saveString(){
        return "D | " + done + " | " + description + " | " + deadline;
    }
}
