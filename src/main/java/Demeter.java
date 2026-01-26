import java.util.Scanner;

public class Demeter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] lst = new Task[100];

        System.out.println("Hello! I'm Demeter.\nWhat can I do for you? \n");

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;

            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < Task.idx; i++) {
                    int line = i + 1;
                    System.out.println(line + ". " + lst[i].printTask());
                }

            } else if (input.startsWith("mark")) {
                int taskNum = Integer.parseInt(input.split(" ")[1]) - 1;
                for (int i = 0; i < Task.idx; i++) {
                    if (lst[i].getId() == taskNum) {
                        lst[i].mark();
                        System.out.println("Nice! I've marked this task as done: \n  " + lst[i].printTask());
                    }
                }

            } else if (input.startsWith("unmark")) {
                int taskNum = Integer.parseInt(input.split(" ")[1]) - 1;
                for (int i = 0; i < Task.idx; i++) {
                    if (lst[i].getId() == taskNum) {
                        lst[i].unmark();
                        System.out.println("Ok, I've marked this task as not done yet: \n  " + lst[i].printTask());
                    }
                }

            } else if (input.startsWith("todo")) {
                lst[Task.idx] = new Todo(input.substring(5),  false);
                printAdd(lst[Task.idx - 1].printTask());

            } else if (input.startsWith("deadline")) {
                String[] parts = input.split(" /by ");
                lst[Task.idx] = new Deadline(parts[0].substring(9), false, " (by: " + parts[1] + ")");
                printAdd(lst[Task.idx - 1].printTask());

            } else if (input.startsWith("event")) {
                String[] parts = input.split(" /from | /to ");
                lst[Task.idx] = new Event(parts[0].substring(6), false, " (from: " + parts[1] + " to: " + parts[2] + ")");
                printAdd(lst[Task.idx - 1].printTask());

            } else {
                System.out.println(input);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }

    private static void printAdd(String task) {
        System.out.println("Got it. I've added this task:\n  " + task);
        System.out.println("Now you have " + (Task.idx) + " tasks in the list.");
    }

    public static class Task {
        private String name;
        private boolean done;
        public static int idx = 0;
        private int id;
        public Task(String name, boolean done) {
            this.name = name;
            this.done = done;
            this.id = idx ++;
        }
        public String printTask() {
            return "";
        }
        public int getId() {
            return this.id;
        }

        public void mark() {
            this.done = true;
        }

        public void unmark() {
            this.done = false;
        }
    }

    public static class Todo extends Task {
        public Todo(String name, boolean done) {
            super(name, done);
        }

        public String printTask() {
            String check = super.done ? "[X] " : "[ ] ";
            return "[T]" + check + super.name;
        }
    }

    public static class Deadline extends Task {
        private String timeInfo;
        public Deadline(String name, boolean done, String timeInfo) {
            super(name, done);
            this.timeInfo = timeInfo;
        }

        public String printTask() {
            String check = super.done ? "[X] " : "[ ] ";
            return "[D]" + check + super.name + this.timeInfo;
        }
    }

    public static class Event extends Task {
        private String timeInfo;
        public Event(String name, boolean done, String timeInfo) {
            super(name, done);
            this.timeInfo = timeInfo;
        }

        public String printTask() {
            String check = super.done ? "[X] " : "[ ] ";
            return "[E]" + check + super.name + this.timeInfo;
        }

    }
}
