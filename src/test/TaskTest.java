import demeter.Task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @BeforeEach
    void resetIndex() {
        Task.idx = 0;
    }

    @Test
    void constructor_setsNameDoneAndIdCorrectly() {
        Task t = new Task("read book", false);

        assertEquals(0, t.getId());
        assertFalse(t.isDone());
    }

    @Test
    void mark_setsTaskToDone() {
        Task t = new Task("task", false);

        t.mark();

        assertTrue(t.isDone());
    }

    @Test
    void unmark_setsTaskToNotDone() {
        Task t = new Task("task", true);

        t.unmark();

        assertFalse(t.isDone());
    }

    @Test
    void id_incrementsForEachTask() {
        Task t1 = new Task("a", false);
        Task t2 = new Task("b", false);

        assertEquals(0, t1.getId());
        assertEquals(1, t2.getId());
    }
}