package astra.system;

import astra.gui.MainWindow;

/**
 * Handles all the Ui for the chatbot.
 */
public class Ui {

    /**
     * Displays the greeting at the start of chatbot running.
     */
    public static void greet() {
        System.out.println("Hello! I'm Astra ^-^");
        System.out.println("What can I do for you?");
        MainWindow.addMessage("Hello! I'm Astra ^-^", "What can I do for you?");
    }

    /**
     * Displays the endings at the end of the chatbot running.
     */
    public static void end() {
        System.out.println("Bye. Hope to see you again soon! ^v^");
        MainWindow.addMessage("What can I do for you?");
    }

    /**
     * Displays the error on the screen.
     * @param error The error message to be displayed.
     */
    public static void feedbackError(String error) {
        System.out.println("An error has been encountered:");
        System.out.println(error);
        MainWindow.addMessage("An error has been encountered:", error);
    }

    /**
     * Displays all the chatbot messages on the screen.
     * This is for terminal user feedback.
     * @param messages The messages to be displayed.
     */
    public static void feedbackMessage(String... messages) {
        for (int i = 0; i < messages.length; i++) {
            System.out.println(messages[i]);
        }
    }
}
