package demeter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private final Path filePath;

    public Storage() {
        this.filePath = Paths.get("data", "demeter.txt");
        assert filePath != null : "File path should not be null";
    }

    public List<Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>();
        assert tasks != null : "Task list should be initialised";

        if (!Files.exists(filePath)) {
            return tasks;
        }

        Files.createDirectories(filePath.getParent());
        Scanner scanner = new Scanner(filePath);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            assert line != null : "Line read from file should not be null";
            assert !line.isBlank() : "Stored task line should not be blank";

            String[] parts = line.split(" \\| ");
            assert parts.length >= 3 : "Stored task format is invalid";

            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            assert description != null : "Task description should not be null";

            switch (type) {
            case "T":
                tasks.add(new Todo(description, isDone));
                break;

            case "D":
                assert parts.length == 4 : "Deadline should have 4 fields";
                tasks.add(new Deadline(description, isDone, parts[3]));
                break;

            case "E":
                assert parts.length == 4 : "Event should have 4 fields";
                String[] times = parts[3].split(" - ");
                assert times.length == 2 : "Event time format invalid";
                tasks.add(new Event(description, isDone, times[0], times[1]));
                break;

            default:
                assert false : "Unknown task type in storage file";
            }
        }

        scanner.close();
        return tasks;
    }

    public void save(List<Task> tasks) throws IOException {
        assert tasks != null : "Task list to save should not be null";

        Files.createDirectories(filePath.getParent());
        BufferedWriter writer = Files.newBufferedWriter(filePath);

        for (Task task : tasks) {
            assert task != null : "Task in list should not be null";
            writer.write(task.printToFile());
            writer.newLine();
        }

        writer.close();
    }
}