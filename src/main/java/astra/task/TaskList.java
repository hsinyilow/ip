package astra.task;

import java.util.ArrayList;

import astra.gui.GuiMain;
import astra.gui.MainWindow;
import astra.gui.MessageBox;
import astra.system.AstraException;
import astra.system.Parser;
import astra.system.SaveSystem;
import astra.system.Ui;
import com.sun.tools.javac.Main;


/**
 * Is a task list.
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();
    private int counter = 0;

    /**
     * Adds a new task the task list.
     * @param input The add task command or remaining commands.
     */
    public void addTask(String input) {
        Task newTask;
        try {
            //create task
            if (input.startsWith("todo") | input.startsWith("T")) {
                newTask = new TodoTask(input);
            } else if (input.startsWith("deadline") | input.startsWith("D")) {
                newTask = new DeadlineTask(input);
            } else if (input.startsWith("event") | input.startsWith("E")) {
                newTask = new EventTask(input);
            } else {
                throw new AstraException("Unknown command");
            }
            tasks.add(newTask);
            SaveSystem.add(newTask.saveString());
            counter++;

            //feedback
            System.out.println("A new task is added:");
            System.out.println(newTask.displayTask());
            System.out.println("You have " + counter + " tasks left! ^_^");

            MainWindow.messageList.add("A new task is added:");
            MainWindow.messageList.add(newTask.displayTask());
            MainWindow.messageList.add("You have " + counter + " tasks left! ^_^");
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
                System.out.println("Sorry, this task don't exist :(");
                MainWindow.messageList.add("Sorry, this task don't exist :(");
            } else {
                String feedback = tasks.get(taskIndex).displayTask();
                tasks.remove(taskIndex);
                SaveSystem.delete(taskIndex);
                counter--;
                System.out.println("This task has been removed:");
                System.out.println(feedback);
                MainWindow.messageList.add("This task has been removed:");
                MainWindow.messageList.add(feedback);
                if (counter == 0) {
                    System.out.println("All tasks have been completed! ^_^");
                    MainWindow.messageList.add("All tasks have been completed! ^_^");
                } else {
                    System.out.println("There are " + counter + " tasks left!");
                    MainWindow.messageList.add("There are " + counter + " tasks left!");
                }

            }
        } catch (AstraException e) {
            //System.out.println(e.getMessage());
            Ui.feedbackError(e.getMessage());
        }
    }

    /**
     * Marks a class as incomplete or complete.
     * @param input The mark command.
     */
    public void markTask(String input) {
        try {
            //mark/unmark task
            boolean mark = input.startsWith("m");
            int taskIndex = Parser.parseIntCommand(input, mark ? 5 : 7) - 1;
            if (taskIndex >= counter || taskIndex < 0) {
                System.out.println("Sorry, this task don't exist :(");
                MainWindow.messageList.add("Sorry, this task don't exist :(");
            } else {
                Task currentTask = tasks.get(taskIndex);
                currentTask.updateMark(mark);
                SaveSystem.update(taskIndex, currentTask.saveString());
            }
        } catch (AstraException e) {
            System.out.println(e.getMessage());
            Ui.feedbackError(e.getMessage());
        }
    }

    /**
     * Displays all the tasks in the task list.
     */
    public void displayTaskList() {
        System.out.println("Here are the tasks in your list:");
        MainWindow.messageList.add("Here are the tasks in your list:");
        for (int i = 0; i < counter; i++) {
            System.out.println((i + 1) + "." + tasks.get(i).displayTask());
            MainWindow.messageList.add((i + 1) + "." + tasks.get(i).displayTask());
        }
    }

    /**
     * Find all tasks that matches the input.
     * @param input The matching task description.
     */
    public void findTask(String input) {
        String parsed = Parser.parseCommand(input, 4, false);

        System.out.println("Here are the matching tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).checkDescription(parsed)) {
                System.out.println(tasks.get(i).displayTask());
                MainWindow.messageList.add(tasks.get(i).displayTask());
            }
        }
    }


    /**
     * Checks and calls the function associated with the given command.
     * @param input The command to be called.
     */
    public void command(String input) {
        if (input.equals("list")) {
            //show list
            displayTaskList();
        } else if (input.startsWith("delete")) {
            deleteTask(input);
        } else if (input.startsWith("mark") || input.startsWith("unmark")) {
            markTask(input);
        } else if (input.startsWith("find")) {
            findTask(input);
        } else {
            //add to list
            addTask(input);
        }
    }

}
