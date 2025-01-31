package astra;

import astra.system.AstraException;
import astra.system.SaveSystem;
import astra.system.Ui;
import astra.task.TaskList;

import java.util.Scanner;

public class Astra {

    private TaskList taskList = new TaskList();


    /**
     * Constructor for Astra.
     *
     * @param filePath file path of save file.
     */
    public Astra(String filePath) {
        //initialize
        SaveSystem.Load(filePath, taskList);
        Ui.Greet();
    }

    /**
     * Chatbot running loop.
     * Wait for user input and run the command.
     * Exits running loop when Bye command is called.
     */
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
