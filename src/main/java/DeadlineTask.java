public class DeadlineTask extends Task{
    protected String deadline;
    public DeadlineTask(String description, String deadline){
        super(description.substring(9, description.length() - 1));
        this.deadline = deadline.substring(3);
    }

    @Override
    public String displayTask() {
        return String.format("[D][%s] %s (by: %s)", (done? "X" : " "), description, deadline);
    }
}
