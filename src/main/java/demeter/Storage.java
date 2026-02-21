package demeter;// demeter.Storage.java
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private final Path filePath;

    public Storage() {
        this.filePath = Paths.get("data/demeter.txt");
    }

    /**
     * Loads all tasks from hard disk into Demeter.
     * If the file does not exist, returns an empty list.
     *
     * @return List of all tasks.
     * @throws IOException if an IO error occurs while reading the file.
     */
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

    /**
     * Saves the given list of tasks to the hard disk.
     * Creates any necessary directories if they do not exist.
     *
     * @param tasks The list of tasks to be saved.
     * @throws IOException if an IO error occurs while writing to the file.
     */
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
