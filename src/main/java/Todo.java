public class Todo {
    Task[] tasks = new Task[100];
    int counter = 0;
    public void Add(String input){
        Task newTask = null;
        if(input.startsWith("todo")){
            newTask = new Task(input.substring(5));
        } else if (input.startsWith("deadline")) {
            String[] parseInput = input.split("/");
            newTask = new DeadlineTask(parseInput[0], parseInput[1]);
        } else {
            String[] parseInput = input.split("/");
            newTask = new EventTask(parseInput[0], parseInput[1], parseInput[2]);
        }
        tasks[counter] = newTask;
        counter++;

        System.out.println("A new task is added:");
        System.out.println(newTask.displayTask());
        System.out.println("You have " + counter + " tasks left! ^_^");
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
        } else if (input.startsWith("unmark")) {
            int taskIndex = Integer.parseInt(input.substring(7)) - 1;
            if (taskIndex >= counter) {
                System.out.println("Sorry, this task don't exist :(");
            } else {
                tasks[taskIndex].updateMark(false);
            }
        } else if (input.startsWith("mark")) {
            int taskIndex = Integer.parseInt(input.substring(5)) - 1;
            if (taskIndex >= counter) {
                System.out.println("Sorry, this task don't exist :(");
            } else {
                tasks[taskIndex].updateMark(true);
            }
        } else {
            //add to list
            Add(input);
        }
    }
}
