import java.util.Scanner;

public class Demeter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] lst = new String[100];
        boolean[] done = new boolean[100];
        int idx = 0;
        System.out.println("Hello! I'm Demeter.\nWhat can I do for you? \n");
        while (true) {
            String input = sc.nextLine();
            // System.out.println(input);

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println("Here are the items in your list: \n");
                for (int i = 0; i < idx; i++) {
                    int line = i + 1;
                    String check = "[ ] ";
                    if (done[i]) {
                        check = "[X] ";
                    }
                    System.out.println(line + ". " + check + lst[i]);
                }
            } else if (input.startsWith("mark")) {
                int taskNum = Integer.parseInt(input.split(" ")[1]) - 1;
                done[taskNum] = true;
                System.out.println("Nice! I've marked this task as done: \n" + "  [X] " + lst[taskNum]);
            } else if (input.startsWith("unmark")) {
                int taskNum = Integer.parseInt(input.split(" ")[1]) - 1;
                done[taskNum] = false;
                System.out.println("Ok, I've marked this task as not done yet: \n" + "  [ ] " + lst[taskNum]);
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
