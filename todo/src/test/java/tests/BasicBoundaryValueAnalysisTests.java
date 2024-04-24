package tests;

import model.Priority;
import model.Task;
import org.junit.Before;
import org.junit.Test;
import services.TaskService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BasicBoundaryValueAnalysisTests {
    TaskService taskService;

    @Before
    public void setUp() {
        taskService = new TaskService();
    }
    // Boundary Value Analysis

    @Test
    public void givenLowestValidIndex_whenGetByIndex_thenRetrieveTask() {
        taskService.addTask(new Task("task1", Priority.HIGH, 1));

        assertEquals("task1", taskService.getByIndex(0).getDescription());
    }

    @Test
    public void givenHighestValidIndex_whenGetByIndex_thenRetrieveTask() {
        taskService.addTask(new Task("task1", Priority.HIGH, 1));
        taskService.addTask(new Task("task2", Priority.HIGH, 2));
        taskService.addTask(new Task("task3", Priority.HIGH, 3));

        assertEquals("task3", taskService.getByIndex(2).getDescription());
    }

    @Test
    public void givenIndexBelowLowest_whenGetByIndex_thenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> taskService.getByIndex(-1));
        assertThrows(IllegalArgumentException.class, () -> taskService.getByIndex(-2));
    }

    @Test
    public void givenIndexAboveHighest_whenGetByIndex_thenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> taskService.getByIndex(1));
    }
}
