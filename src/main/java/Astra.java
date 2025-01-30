import java.util.Scanner;

public class Astra {

    private TaskList taskList = new TaskList();


    public Astra(String filePath) {
        //initialize
        SaveSystem.Load(filePath, taskList);
        Ui.Greet();
    }

    public void Run() {
        Scanner scanner = new Scanner(System.in);

        //chat loop
        while(true){
            String nextLine = scanner.nextLine();

            //end
            if(nextLine.equals("bye")){
                break;
            }
            taskList.Command(nextLine);
        }

        //close chat
        scanner.close();
        Ui.End();
    }

    public static void main(String[] args) {
        new Astra("data/astraData.txt").Run();
    }
}
