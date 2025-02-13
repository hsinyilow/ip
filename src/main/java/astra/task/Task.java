package astra.task;

import astra.gui.MainWindow;
import astra.system.AstraException;
import astra.system.Ui;

/**
 * Is inherited by all tasks.
 */
public abstract class Task {
    protected String description = "";
    protected boolean isDone = false;

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
     * @param isDone The new completion state of the task.
     */
    public void updateMark(boolean isDone) {
        this.isDone = isDone;
        if (isDone) {
            Ui.feedbackMessage("Marking this task as done:", displayTask());
            MainWindow.addMessage("Marking this task as done:", displayTask());

        } else {
            Ui.feedbackMessage("Marking this task as not done:", displayTask());
            MainWindow.addMessage("Marking this task as not done:", displayTask());
        }
    }

    /**
     * Updates the task with new information.
     * @param input possible changes made to the tasks.
     * @throws AstraException If the provided type of detail does not exist.
     */
    abstract void updateDetails(String input) throws AstraException;

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
