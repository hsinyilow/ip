import java.util.ArrayList;

public class TodoList {
    ArrayList<Task> tasks = new ArrayList<>();
    int counter = 0;
    public void AddTask(String input){
        Task newTask;
        try{
            //create task
            if(input.startsWith("todo")){
                newTask = new TodoTask(input);
            } else if (input.startsWith("deadline")) {
                newTask = new DeadlineTask(input);
            } else if(input.startsWith("event")) {
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
        } catch (AstraException ae){
            System.out.println(ae.getMessage());
        }

    }

    public void DeleteTask(String input){
        try{
            int taskIndex = CheckIntCommand(input, 6) - 1;
            if (taskIndex >= counter  || taskIndex < 0) {
                System.out.println("Sorry, this task don't exist :(");
            } else {
                String feedback = tasks.get(taskIndex).displayTask();
                tasks.remove(taskIndex);
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

    public void Mark(String input){
        try{
            //mark/unmark task
            boolean mark = input.startsWith("m");
            int taskIndex = CheckIntCommand(input, mark? 5: 7) - 1;
            if (taskIndex >= counter || taskIndex < 0) {
                System.out.println("Sorry, this task don't exist :(");
            } else {
                tasks.get(taskIndex).updateMark(mark);
            }
        } catch (AstraException e){
            System.out.println(e.getMessage());
        }
    }

    public void DisplayList(){
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < counter; i++) {
            System.out.println((i + 1) + "." + tasks.get(i).displayTask());
        }
    }

    public void Command(String input){
        if(input.equals("list")) {
            //show list
            DisplayList();
        } else if (input.startsWith("delete")) {
            DeleteTask(input);
        } else if (input.startsWith("mark") || input.startsWith("unmark")) {
            Mark(input);
        } else {
            //add to list
            AddTask(input);
        }
    }

    protected String commandCheck(String command, int min){
        if(command.length() <= min) return "";
        command = command.substring(min);
        command = command.replace(" ", "");
        return command;
    }

    protected int CheckIntCommand(String command, int min) throws AstraException{
        command = commandCheck(command, min);
        if (command.isEmpty()) {
            throw new AstraException("This is an invalid command");
        }

        try{
            return Integer.parseInt(command);

        } catch (NumberFormatException e){
            throw new AstraException("This command requires a number");
        }
    }
}
