import java.util.Scanner;

public class Demeter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Demeter.\nWhat can I do for you? \n");
        while (true) {
            String input = sc.nextLine();
            System.out.println(input);

            if (input.equals("bye")) {
                break;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }
}
