// Storage.java
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private final Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    public List<Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>();

        if (!Files.exists(filePath)) {
            return tasks;
        }

        Files.createDirectories(filePath.getParent());
        Scanner scanner = new Scanner(filePath);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" \\| ");

            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            switch (type) {
                case "T":
                    tasks.add(new Todo(description, isDone));
                    break;
                case "D":
                    tasks.add(new Deadline(description, isDone, parts[3]));
                    break;
                case "E":
                    String[] times = parts[3].split(" - ");
                    tasks.add(new Event(description, isDone, times[0], times[1]));
                    break;
            }
        }

        scanner.close();
        return tasks;
    }

    public void save(List<Task> tasks) throws IOException {
        Files.createDirectories(filePath.getParent());
        BufferedWriter writer = Files.newBufferedWriter(filePath);

        for (Task task : tasks) {
            writer.write(task.printToFile());
            writer.newLine();
        }

        writer.close();
    }
}
