public class TodoTask extends Task{

    public TodoTask(String description) throws AstraException{
        super();
        String result = commandCheck(description, 4);
        if (result.isEmpty()){
            throw new AstraException("Invalid task description");
        }
        this.description = result;
    }

    @Override
    protected String saveString(){
        return "T | " + done + " | " + description;
    }
}
