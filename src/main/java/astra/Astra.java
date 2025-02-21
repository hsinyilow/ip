package astra;

import java.io.File;
import java.util.Scanner;

import astra.system.SaveSystem;
import astra.system.Ui;
import astra.task.TaskList;



/**
 * Starts the chatbot for CLI users and testing.
 */
public class Astra {

    private TaskList taskList = new TaskList();

    /**
     * Initializes the chatbot.
     *
     * @param filePath file path of save file.
     */
    public Astra(String filePath) {
        SaveSystem.loadSaveFile(filePath, taskList);
        Ui.greetUser();
    }

    /**
     * Runs the command and feedback loop.
     */
    public void runAstra() {
        Scanner scanner = new Scanner(System.in);

        /* execute all command user gives */
        while (true) {
            String nextLine = scanner.nextLine();

            if (nextLine.equals("bye")) {
                break;
            }
            taskList.command(nextLine);
        }

        /*End the conversation */
        scanner.close();
        Ui.sayGoodbye();
    }


    public static void main(String[] args) {
        String filePath = "data/astraData.txt";
        new Astra(filePath).runAstra();
    }
}
