package astra.system;

import astra.gui.GuiMain;
import astra.gui.MainWindow;

/**
 * Handles all the Ui for the chatbot.
 */
public class Ui {

    /**
     * Displays the greeting at the start of chatbot running.
     * Caters to both CLI and application interface.
     */
    public static void greetUser() {
        System.out.println("Hello! I'm Astra ^-^");
        System.out.println("What can I do for you?");

        MainWindow.addMessage(
                "Hello! I'm Astra ^-^",
                "How can I help you?",
                "Tip: For the list of commands, type 'help'"
        );
    }

    /**
     * Displays the goodbye message at the end of the chatbot running.
     */
    public static void sayGoodbye() {
        System.out.println("Bye. Hope to see you again soon! ^v^");
        MainWindow.addMessage("Bye. Hope to see you again soon! ^v^");
        GuiMain.closeApp();
    }

    /**
     * Displays the help menu on the application screen.
     */
    public static void displayHelpMenu() {
        MainWindow.addMessage(
                "Here are the list of commands that are available:",
                "'bye': close the application",
                "'list': list all the tasks",
                "'todo [task description]': creates a todo task with the description",
                "'deadline [task description] /by [YYYY-MM-DD] [hour:minutes]': "
                        + "creates a deadline task with the description and the specified deadline (time is optional)",
                "'event [task description] /from [YYYY-MM-DD] [hour:minutes] /to [YYYY-MM-DD] [hour:minutes]': "
                        + "creates an event task with the description and duration (time is optional)",
                "'delete [task number]': delete the specified task",
                "'mark [task number]': mark the specified task as complete",
                "'unmark [task number]': mark the specified task as incomplete",
                "'find [string]': displays all the tasks which description matches the input",
                "'update [task number] /[task detail] [new update]': updates the specified task with the new detail.",
                "    The available task details are:",
                "    - desc: for all type of tasks",
                "    - by: for deadline tasks",
                "    - from: for event tasks",
                "    - to: for event tasks"
        );
    }

    /**
     * Displays the error on the screen.
     *
     * @param error The error message to be displayed.
     */
    public static void displayAstraError(String error) {
        System.out.println("An error has been encountered:");
        System.out.println(error);

        MainWindow.addMessage("An error has been encountered:", error);
    }

    /**
     * Displays all the chatbot messages on the screen.
     * This is for terminal user feedback.
     *
     * @param messages The messages to be displayed.
     */
    public static void displayMessage(String... messages) {
        for (int i = 0; i < messages.length; i++) {
            System.out.println(messages[i]);
        }
    }
}
