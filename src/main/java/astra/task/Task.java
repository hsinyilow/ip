package astra.task;

public abstract class Task {
    protected String description = "";
    protected boolean done = false;

    public void updateMark(boolean updateState){
        done = updateState;
        if (done) {
            System.out.println("Marking this task as done:");
        } else {
            System.out.println("Marking this task as not done:");
        }
        System.out.println(displayTask());;
    }

    abstract String displayTask();
    abstract String saveString();
}
