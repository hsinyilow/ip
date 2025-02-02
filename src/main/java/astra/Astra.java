package astra;

import java.util.Scanner;

import astra.system.SaveSystem;
import astra.system.Ui;
import astra.task.TaskList;



/**
 * Is the chatbot
 */
public class Astra {

    private TaskList taskList = new TaskList();

    /**
     * Initializes the chatbot.
     * @param filePath file path of save file.
     */
    public Astra(String filePath) {
        SaveSystem.loadSaveFile(filePath, taskList);
        Ui.greet();
    }

    /**
     * Command and result loop
     */
    public void runAstra() {
        Scanner scanner = new Scanner(System.in);

        //chat loop
        while (true) {
            String nextLine = scanner.nextLine();

            //end
            if (nextLine.equals("bye")) {
                break;
            }
            taskList.command(nextLine);
        }

        //close chat
        scanner.close();
        Ui.end();
    }

    public static void main(String[] args) {
        new Astra("data/astraData.txt").runAstra();
    }
}
