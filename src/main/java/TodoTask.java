public class TodoTask extends Task {

    public TodoTask(String input) throws AstraException {
        if (input.startsWith("T ")) {
            //load save
            String[] parseInput = Parser.ParseSaveFile(input);
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

    @Override
    protected String saveString(){
        return String.format("T | %b | %s", done, description);
    }

    @Override
    public String displayTask(){
        return String.format("[T][%s] %s", (done? "X" : " "), description);
    }
}
