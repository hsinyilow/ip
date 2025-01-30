public class TodoTask extends Task{

    public TodoTask(String input) throws AstraException{
        super();

        if(input.startsWith("T ")) {
            String[] parseInput = input.split(" \\Q|\\E ");
            this.description = parseInput[2];
            this.done = parseInput[1].equals("true");

        } else {
            String result = commandCheck(input, 4);
            if (result.isEmpty()){
                throw new AstraException("Invalid task description");
            }
            this.description = result;
        }


    }

    @Override
    protected String saveString(){
        return "T | " + done + " | " + description;
    }
}
