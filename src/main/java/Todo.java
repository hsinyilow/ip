public class Todo {
    Task[] tasks = new Task[100];
    int counter = 0;
    public void Add(String input){
        tasks[counter] = new Task(input);
        counter++;

        System.out.println("added: " + input);
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
