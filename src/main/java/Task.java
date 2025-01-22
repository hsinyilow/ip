public class Task {
    protected String description;
    protected boolean done;

    protected Task(String description){
        this.description = description;
        done = false;
    }

    public String displayTask(){
        return String.format("[T][%s] %s", (done? "X" : " "), description);
    }

    public void updateMark(boolean updateState){
        done = updateState;
        if (done) {
            System.out.println("Marking this task as done:");
        } else {
            System.out.println("Marking this task as not done:");
        }
        System.out.println(displayTask());;
    }
}
