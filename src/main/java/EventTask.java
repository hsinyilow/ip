public class EventTask extends Task{
    protected String start;
    protected String end;
    public EventTask(String description, String start, String end){
        super(description.substring(6, description.length() - 1));
        this.start = start.substring(5, start.length() - 1);
        this.end = end.substring(3);
    }

    @Override
    public String displayTask() {
        return String.format("[E][%s] %s (from: %s to: %s)", (done? "X" : " "), description, start, end);
    }
}
