import java.util.Scanner;

public class Demeter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] lst = new String[100];
        int idx = 0;
        System.out.println("Hello! I'm Demeter.\nWhat can I do for you? \n");
        while (true) {
            String input = sc.nextLine();
            // System.out.println(input);

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < idx; i++) {
                    int listnumber = i + 1;
                    System.out.println(listnumber + ". " + lst[i]);
                }
            } else {
                lst[idx] = input;
                idx += 1;
                System.out.println("added: " + input);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }
}
