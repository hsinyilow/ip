public class Todo {
    String[] tasks = new String[100];
    int counter = 0;
    public void Add(String input){
        tasks[counter] = input;
        counter++;

        System.out.println("added: " + input);
    }

    public void DisplayList(){
        for (int i = 0; i < counter; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
    }

    public void Command(String input){
        if(input.equals("list")) {
            //show list
            DisplayList();
        }else{
            //add to list
            Add(input);
        }
    }
}
