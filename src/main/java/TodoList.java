public class TodoList {
    Task[] tasks = new Task[100];
    int counter = 0;
    public void Add(String input){
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
            tasks[counter] = newTask;
            counter++;

            //feedback
            System.out.println("A new task is added:");
            System.out.println(newTask.displayTask());
            System.out.println("You have " + counter + " tasks left! ^_^");
        } catch (AstraException ae){
            System.out.println(ae.getMessage());
        }

    }

    public void Mark(String input){
        try{
            //mark/unmark task
            boolean mark = input.startsWith("m");
            int taskIndex = CheckIntCommand(input, mark? 5: 7) - 1;
            if (taskIndex >= counter) {
                System.out.println("Sorry, this task don't exist :(");
            } else {
                tasks[taskIndex].updateMark(mark);
            }
        } catch (AstraException e){
            System.out.println(e.getMessage());
        }


    }

    public void DisplayList(){
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < counter; i++) {
            System.out.println((i + 1) + "." + tasks[i].displayTask());
        }
    }

    public void Command(String input){
        if(input.equals("list")) {
            //show list
            DisplayList();
        } else if (input.startsWith("mark") || input.startsWith("unmark")) {
            Mark(input);
        } else {
            //add to list
            Add(input);
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
