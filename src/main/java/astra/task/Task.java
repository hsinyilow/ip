package astra.task;

import astra.system.AstraException;

public abstract class Task {
    protected String description = "";
    protected boolean done = false;

    /**
     * Mark task in task list.
     * Mark this task as done or not done.
     *
     * @param updateState if the task is to be mark as done.
     */
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
