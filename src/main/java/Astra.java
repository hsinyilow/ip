import java.util.Scanner;

public class Astra {
    public static void Greet(){
        System.out.println("Hello! I'm Astra ^-^");
        System.out.println("What can I do for you?");
    }

    public static void End(){
        System.out.println("Bye. Hope to see you again soon! ^v^");
    }

    public static void main(String[] args) {
        //initialize
        Echo e = new Echo();
        Scanner scanner = new Scanner(System.in);

        Greet();

        //chat loop
        while(true){
            String nextLine = scanner.nextLine();

            //end
            if(nextLine.equals("bye")){
                End();
                break;
            }
            e.Echoing(nextLine);
        }

        scanner.close();
    }
}
