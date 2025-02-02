package astra.task;

/**
 * Is inherited by all tasks.
 */
public abstract class Task {
    protected String description = "";
    protected boolean done = false;

    /**
     * Checks the description of this task.
     * @param fragment The string that the description needs to match.
     * @return Whether it matches or not.
     */
    public boolean checkDescription(String fragment) {
        return description.contains(fragment);
    }

    /**
     * Updates the completion status of a task.
     * @param updateState The new completion state of the task.
     */
    public void updateMark(boolean updateState) {
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
