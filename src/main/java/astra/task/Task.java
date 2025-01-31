package astra.task;

public abstract class Task {
    protected String description = "";
    protected boolean done = false;

<<<<<<< HEAD
    public void updateMark(boolean updateState) {
=======
    public boolean checkDescription (String fragment) {
        return description.contains(fragment);
    }

    public void updateMark(boolean updateState){
>>>>>>> branch-Level-9
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
