package tests;

import model.Priority;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.TaskService;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class FindWhatTaskToDoNextTestBoundaryValueAnalysisTests {
    TaskService taskService;

    @BeforeEach
    public void setUp() {
        taskService = new TaskService();
    }

    @Test
    void givenLowestValidIndex_whenFindWhatTaskToDoNext_thenFindTask() {
        // prepare data
        taskService.addTask(new Task("task1", Priority.HIGH, 1));
        Integer numberOfTasks = 1;

        assertThrows(IllegalArgumentException.class, () -> taskService.findWhatTaskToDoNext(0));
    }
}
