package astra.task;

public abstract class Task {
    protected String description = "";
    protected boolean done = false;

<<<<<<< HEAD
<<<<<<< HEAD
    public void updateMark(boolean updateState) {
=======
    public boolean checkDescription (String fragment) {
        return description.contains(fragment);
    }

=======
    /**
     * Updates the completion status of a task.
     * @param updateState The new completion state of the task.
     */
>>>>>>> branch-A-JavaDocNew
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

    /**
     * Formats the data in display format.
     * @return Formatted data string.
     */
    abstract String displayTask();

    /**
     * Formats the data in save format.
     * @return Formatted data string.
     */
    abstract String saveString();
}
