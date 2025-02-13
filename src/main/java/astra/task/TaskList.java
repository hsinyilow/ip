package astra.task;

import java.util.ArrayList;

import astra.gui.MainWindow;
import astra.system.AstraException;
import astra.system.Parser;
import astra.system.SaveSystem;
import astra.system.Ui;


/**
 * Is a task list.
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();
    private int counter = 0;
    public enum TaskType {TODO, DEADLINE, EVENT}
    /**
     * Adds a new task the task list.
     * @param input The add task command or remaining commands.
     */
    public void addTask(String input, TaskType taskType) {
        Task newTask;
        try {

            switch (taskType) {
            case TODO:
                newTask = TodoTask.createNewTask(input);
                break;
            case DEADLINE:
                newTask = DeadlineTask.createNewTask(input);
                break;
            case EVENT:
                newTask = EventTask.createNewTask(input);
                break;
            default:
                Ui.feedbackError("Unknown task type");
                return;
            }

            tasks.add(newTask);
            SaveSystem.add(newTask.saveString());
            counter++;

            Ui.feedbackMessage("A new task is added:",
                    newTask.displayTask(), "You have " + counter + " tasks left! ^_^");

            MainWindow.addMessage("A new task is added:",
                    newTask.displayTask(), "You have " + counter + " tasks left! ^_^");

        } catch (AstraException e) {
            Ui.feedbackError(e.getMessage());
        }

    }

    /**
     * Deletes a task from the task list.
     * @param input The delete task command.
     */
    public void deleteTask(String input) {
        try {
            int taskIndex = Parser.parseIntCommand(input, 6) - 1;

            if (taskIndex >= counter || taskIndex < 0) {
                Ui.feedbackMessage("Sorry, this task don't exist :(");
                MainWindow.addMessage("Sorry, this task don't exist :(");
            } else {

                String feedback = tasks.get(taskIndex).displayTask();
                tasks.remove(taskIndex);
                SaveSystem.delete(taskIndex);
                counter--;


                Ui.feedbackMessage("This task has been removed:", feedback);
                MainWindow.addMessage("This task has been removed:", feedback);

                if (counter == 0) {
                    Ui.feedbackMessage("All tasks have been completed! ^_^");
                    MainWindow.addMessage("All tasks have been completed! ^_^");
                } else {
                    Ui.feedbackMessage("There are " + counter + " tasks left!");
                    MainWindow.addMessage("There are " + counter + " tasks left!");
                }

            }
        } catch (AstraException e) {
            Ui.feedbackError(e.getMessage());
        }
    }

    /**
     * Marks a class as incomplete or complete.
     * @param input The mark command.
     */
    public void markTask(String input) {
        try {
            boolean mark = input.startsWith("m");
            int taskIndex = Parser.parseIntCommand(input, mark ? 5 : 7) - 1;

            if (taskIndex >= counter || taskIndex < 0) {
                Ui.feedbackMessage("Sorry, this task don't exist :(");
                MainWindow.addMessage("Sorry, this task don't exist :(");
            } else {
                Task currentTask = tasks.get(taskIndex);
                currentTask.updateMark(mark);
                SaveSystem.update(taskIndex, currentTask.saveString());
            }

        } catch (AstraException e) {
            Ui.feedbackError(e.getMessage());
            MainWindow.addMessage(e.getMessage());
        }
    }

    /**
     * Displays all the tasks in the task list.
     */
    public void displayTaskList() {
        Ui.feedbackMessage("Here are the tasks in your list:");
        MainWindow.addMessage("Here are the tasks in your list:");

        for (int i = 0; i < counter; i++) {
            Ui.feedbackMessage((i + 1) + "." + tasks.get(i).displayTask());
            MainWindow.addMessage((i + 1) + "." + tasks.get(i).displayTask());
        }
    }

    /**
     * Find all tasks that matches the input.
     * @param input The matching task description.
     */
    public void findTask(String input) {
        String parsed = Parser.parseCommand(input, 4, false);

        Ui.feedbackMessage("Here are the matching tasks in your list:");
        MainWindow.addMessage("Here are the matching tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).checkDescription(parsed)) {
                Ui.feedbackMessage(tasks.get(i).displayTask());
                MainWindow.addMessage(tasks.get(i).displayTask());
            }
        }
    }


    /**
     * Checks and calls the function associated with the given command.
     * @param input The command to be called.
     */
    public void command(String input) {
        if (input.equals("list")) {
            displayTaskList();
        } else if (input.startsWith("delete")) {
            deleteTask(input);
        } else if (input.startsWith("mark") || input.startsWith("unmark")) {
            markTask(input);
        } else if (input.startsWith("find")) {
            findTask(input);
        } else if (input.startsWith("todo") | input.startsWith("T")) {
            addTask(input, TaskType.TODO);
        } else if (input.startsWith("deadline") | input.startsWith("D")) {
            addTask(input, TaskType.DEADLINE);
        } else if (input.startsWith("event") | input.startsWith("E")) {
            addTask(input, TaskType.EVENT);
        } else {
            Ui.feedbackError("Unknown command");
        }
    }

}
