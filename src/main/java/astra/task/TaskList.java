package astra.task;

import astra.system.AstraException;
import astra.system.Parser;
import astra.system.SaveSystem;

import java.util.ArrayList;

public class TaskList {
   ArrayList<Task> tasks = new ArrayList<>();
   int counter = 0;
<<<<<<< HEAD
   public void AddTask(String input) {
=======

    /**
     * Adds a new task the task list.
     * @param input The add task command or remaining commands.
     */
   public void AddTask(String input){
>>>>>>> branch-A-JavaDocNew
        Task newTask;
        try{
            //create task
            if(input.startsWith("todo") | input.startsWith("T")) {
                newTask = new TodoTask(input);
            } else if (input.startsWith("deadline") | input.startsWith("D")) {
                newTask = new DeadlineTask(input);
            } else if(input.startsWith("event") | input.startsWith("E")) {
                newTask = new EventTask(input);
            } else{
                throw new AstraException("Unknown command");
            }
            tasks.add(newTask);
            SaveSystem.Add(newTask.saveString());
            counter++;

            //feedback
            System.out.println("A new task is added:");
            System.out.println(newTask.displayTask());
            System.out.println("You have " + counter + " tasks left! ^_^");
        } catch (AstraException ae) {
            System.out.println(ae.getMessage());
        }

    }

    /**
     * Deletes a task from the task list.
     * @param input The delete task command.
     */
    public void DeleteTask(String input){
        try{
            int taskIndex = Parser.ParseIntCommand(input, 6) - 1;
            if (taskIndex >= counter  || taskIndex < 0) {
                System.out.println("Sorry, this task don't exist :(");
            } else {
                String feedback = tasks.get(taskIndex).displayTask();
                tasks.remove(taskIndex);
                SaveSystem.Delete(taskIndex);
                counter--;
                System.out.println("This task has been removed:");
                System.out.println(feedback);
                if(counter == 0){
                    System.out.println("All tasks have been completed! ^_^");
                } else{
                    System.out.println("There are " + counter + " tasks left!");
                }

            }
        } catch (AstraException e){
            System.out.println(e.getMessage());
        }
    }

<<<<<<< HEAD
    public void Mark(String input) {
        try {
=======
    /**
     * Marks a class as incomplete or complete.
     * @param input The mark command.
     */
    public void Mark(String input){
        try{
>>>>>>> branch-A-JavaDocNew
            //mark/unmark task
            boolean mark = input.startsWith("m");
            int taskIndex = Parser.ParseIntCommand(input, mark? 5: 7) - 1;
            if (taskIndex >= counter || taskIndex < 0) {
                System.out.println("Sorry, this task don't exist :(");
            } else {
                Task currentTask = tasks.get(taskIndex);
                currentTask.updateMark(mark);
                SaveSystem.Update(taskIndex, currentTask.saveString());
            }
        } catch (AstraException e) {
            System.out.println(e.getMessage());
        }
    }

<<<<<<< HEAD
    public void DisplayList() {
=======
    /**
     * Displays all the tasks in the task list.
     */
    public void DisplayList(){
>>>>>>> branch-A-JavaDocNew
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < counter; i++) {
            System.out.println((i + 1) + "." + tasks.get(i).displayTask());
        }
    }

<<<<<<< HEAD
<<<<<<< HEAD
    public void Command(String input) {
=======
    public void Find(String input) {
        String parsed = Parser.ParseCommand(input, 4, false);

        System.out.println("Here are the matching tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).checkDescription(parsed)) {
                System.out.println(tasks.get(i).displayTask());
            }
        }
    }

=======
    /**
     * Checks and calls the function associated with the given command.
     * @param input The command to be called.
     */
>>>>>>> branch-A-JavaDocNew
    public void Command(String input){
>>>>>>> branch-Level-9
        if(input.equals("list")) {
            //show list
            DisplayList();
        } else if (input.startsWith("delete")) {
            DeleteTask(input);
        } else if (input.startsWith("mark") || input.startsWith("unmark")) {
            Mark(input);
        } else if (input.startsWith("find") ) {
            Find(input);
        } else {
            //add to list
            AddTask(input);
        }
    }

}
