import demeter.Task;
import demeter.Todo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    @BeforeEach
    void resetIndex() {
        Task.idx = 0;
    }

    @Test
    void printTask_notDone_correctFormat() {
        Todo todo = new Todo("read book", false);

        assertEquals("[T][ ] read book", todo.printTask());
    }

    @Test
    void printTask_done_correctFormat() {
        Todo todo = new Todo("read book", true);

        assertEquals("[T][X] read book", todo.printTask());
    }

    @Test
    void printToFile_notDone_correctFormat() {
        Todo todo = new Todo("read book", false);

        assertEquals("T | 1 | read book", todo.printToFile());
    }

    @Test
    void printToFile_done_correctFormat() {
        Todo todo = new Todo("read book", true);

        assertEquals("T | 0 | read book", todo.printToFile());
    }
}