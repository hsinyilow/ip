package astra.system;

public class Ui {

    public static void Greet() {
        System.out.println("Hello! I'm astra.Astra ^-^");
        System.out.println("What can I do for you?");
    }

    public static void End() {
        System.out.println("Bye. Hope to see you again soon! ^v^");
    }

    public static void FeedbackError(String error) {
        System.out.println("An error has been encountered:");
        System.out.println(error);
    }
}
