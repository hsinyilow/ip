package astra.system;

public class Ui {

<<<<<<< HEAD
    public static void Greet() {
=======
    /**
     * Displays the greeting at the start of chatbot running.
     */
    public static void Greet(){
>>>>>>> branch-A-JavaDocNew
        System.out.println("Hello! I'm astra.Astra ^-^");
        System.out.println("What can I do for you?");
    }

<<<<<<< HEAD
    public static void End() {
        System.out.println("Bye. Hope to see you again soon! ^v^");
    }

    public static void FeedbackError(String error) {
=======
    /**
     * Displays the endings at the end of the chatbot running.
     */
    public static void End(){
        System.out.println("Bye. Hope to see you again soon! ^v^");
    }

    /**
     * Displays the error on the screen.
     * @param error The error message to be displayed.
     */
    public static void FeedbackError(String error){
>>>>>>> branch-A-JavaDocNew
        System.out.println("An error has been encountered:");
        System.out.println(error);
    }
}
