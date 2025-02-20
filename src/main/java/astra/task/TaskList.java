package astra.task;

import java.util.ArrayList;

import astra.gui.MainWindow;
import astra.system.AstraException;
import astra.system.Parser;
import astra.system.SaveSystem;
import astra.system.Ui;


/**
 * Handles the task list.
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();
    private int counter = 0;

    /**
     * Holds all the current types of task available.
     */
    public enum TaskType { TODO, DEADLINE, EVENT }

    /**
     * Adds a new task the task list.
     *
     * @param input The add task command.
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
                Ui.displayAstraError("Unknown task type");
                return;
            }

            tasks.add(newTask);
            SaveSystem.addData(newTask.saveString());
            counter++;

            Ui.displayMessage("A new task is added:",
                    newTask.displayTask(), "You have " + counter + " tasks left! ^_^");

            MainWindow.addMessage("A new task is added:",
                    newTask.displayTask(), "You have " + counter + " tasks left! ^_^");

        } catch (AstraException e) {
            Ui.displayAstraError(e.getMessage());
        }
    }

    /**
     * Deletes a task from the task list.
     *
     * @param input The delete task command.
     */
    public void deleteTask(String input) {
        try {
            int taskIndex = Parser.parseIntCommand(input, 6) - 1;

            if (taskIndex >= counter || taskIndex < 0) {
                Ui.displayMessage("Sorry, this task don't exist :(");
                MainWindow.addMessage("Sorry, this task don't exist :(");
            } else {

                String feedback = tasks.get(taskIndex).displayTask();
                tasks.remove(taskIndex);
                SaveSystem.deleteData(taskIndex);
                counter--;


                Ui.displayMessage("This task has been removed:", feedback);
                MainWindow.addMessage("This task has been removed:", feedback);

                if (counter == 0) {
                    Ui.displayMessage("All tasks have been completed! ^_^");
                    MainWindow.addMessage("All tasks have been completed! ^_^");
                } else {
                    Ui.displayMessage("There are " + counter + " tasks left!");
                    MainWindow.addMessage("There are " + counter + " tasks left!");
                }

            }
        } catch (AstraException e) {
            Ui.displayAstraError(e.getMessage());
        }
    }

    /**
     * Marks a class as incomplete or complete.
     *
     * @param input The mark command.
     */
    public void markTask(String input) {
        try {
            boolean mark = input.startsWith("m");
            int taskIndex = Parser.parseIntCommand(input, mark ? 5 : 7) - 1;

            if (taskIndex >= counter || taskIndex < 0) {
                Ui.displayMessage("Sorry, this task don't exist :(");
                MainWindow.addMessage("Sorry, this task don't exist :(");
            } else {
                Task currentTask = tasks.get(taskIndex);
                currentTask.updateMark(mark);
                SaveSystem.updateData(taskIndex, currentTask.saveString());
            }

        } catch (AstraException e) {
            Ui.displayAstraError(e.getMessage());
            MainWindow.addMessage(e.getMessage());
        }
    }

    /**
     * Tries to update the specified task with new information.
     * It can display an error if the new information is invalid or the command is invalid.
     *
     * @param input The full update task command in the format: update [list number] /[taskSection] [new update]
     */
    public void updateTask(String input) {
        String[] splitCommand = input.split("/");

        try {
            int taskIndex = Parser.parseIntCommand(splitCommand[0], 7) - 1;
            Task currentTask = tasks.get(taskIndex);
            currentTask.updateDetails(splitCommand[1]);

            SaveSystem.updateData(taskIndex, currentTask.saveString());

        } catch (AstraException e) {
            Ui.displayAstraError(e.getMessage());
        }
    }

    /**
     * Displays all the tasks in the task list.
     */
    public void displayTaskList() {
        Ui.displayMessage("Here are the tasks in your list:");
        MainWindow.addMessage("Here are the tasks in your list:");

        for (int i = 0; i < counter; i++) {
            Ui.displayMessage((i + 1) + "." + tasks.get(i).displayTask());
            MainWindow.addMessage((i + 1) + "." + tasks.get(i).displayTask());
        }
    }

    /**
     * Finds all the tasks that matches the input.
     *
     * @param input The matching task description.
     */
    public void findTask(String input) {
        String parsed = Parser.parseCommand(input, 4, false);

        Ui.displayMessage("Here are the matching tasks in your list:");
        MainWindow.addMessage("Here are the matching tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).checkDescription(parsed)) {
                Ui.displayMessage(tasks.get(i).displayTask());
                MainWindow.addMessage(tasks.get(i).displayTask());
            }
        }
    }


    /**
     * Checks and calls the function associated with the given command.
     *
     * @param input The command to be called.
     */
    public void command(String input) {
        if (input.equals("list")) {
            displayTaskList();
        } else if (input.startsWith("delete")) {
            deleteTask(input);
        } else if (input.startsWith("update")) {
            updateTask(input);
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
            Ui.displayAstraError("Unknown command");
        }
    }

}
