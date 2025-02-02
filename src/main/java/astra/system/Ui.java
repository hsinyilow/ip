package astra.system;

public class Ui {

    /**
     * Displays the greeting at the start of chatbot running.
     */
    public static void greet() {
        System.out.println("Hello! I'm astra.Astra ^-^");
        System.out.println("What can I do for you?");
    }

    /**
     * Displays the endings at the end of the chatbot running.
     */
    public static void end() {
        System.out.println("Bye. Hope to see you again soon! ^v^");
    }

    /**
     * Displays the error on the screen.
     * @param error The error message to be displayed.
     */
    public static void feedbackError(String error) {
        System.out.println("An error has been encountered:");
        System.out.println(error);
    }
}
