package tests;

import model.Priority;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.TaskService;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class RecommendTaskEquivalencePartitioningTests {
    TaskService taskService;

    @BeforeEach
    public void setUp() {
        taskService = new TaskService();
        //prepare data
        taskService.addTask(new Task("Task 1", Priority.HIGH, 3));
        taskService.addTask(new Task("Task 2", Priority.MEDIUM, 2));
        taskService.addTask(new Task("Task 4", Priority.HIGH, 5));
        taskService.addTask(new Task("Task 5", Priority.MEDIUM, 4));
        taskService.addTask(new Task("Task 7", Priority.HIGH, 7));
        taskService.addTask(new Task("Task 8", Priority.MEDIUM, 6));
        taskService.addTask(new Task("Task 10", Priority.HIGH, 9));
        taskService.addTask(new Task("Task 11", Priority.MEDIUM, 8));
        taskService.addTask(new Task("Task 13", Priority.HIGH, 11));
        taskService.addTask(new Task("Task 16", Priority.HIGH, 13));
        taskService.addTask(new Task("Task 17", Priority.MEDIUM, 12));
        taskService.addTask(new Task("Task 19", Priority.HIGH, 15));
        taskService.addTask(new Task("Task 20", Priority.MEDIUM, 14));
    }

    @Test
    void givenNegativePriority_whenRecommendTask_thenThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> taskService.recommendTask(-1, 1));
        assertEquals("Invalid priority", exception.getMessage());
    }

    @Test
    void givenNegativeTimeEstimate_whenRecommendTask_thenThrowException() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> taskService.recommendTask(1, -1));
        assertEquals("Invalid time estimate", exception.getMessage());
    }

    @Test
    void givenValidValues_whenRecommendTask_thenRecommendTask() {
        Task recommendedTask = taskService.recommendTask(3, 20);
        Task expectedTask = taskService.getByIndex(0);

        assertEquals(recommendedTask, expectedTask);
    }

    @Test
    void givenNonExistingPriority_whenRecommendTask_thenRecommendTask() {
        NoSuchElementException exception = assertThrows(
                NoSuchElementException.class, () -> taskService.recommendTask(1, 20));
        assertEquals("No task with required properties found", exception.getMessage());
    }

    @Test
    void givenNonExistingTimeEstimate_whenRecommendTask_thenThrowException() {
        NoSuchElementException exception =
                assertThrows(NoSuchElementException.class, () -> taskService.recommendTask(3, 1));
        assertEquals("No task with required properties found", exception.getMessage());
    }

    @Test
    void givenNonExistingPriorityAndTimeEstimate_whenRecommendTask_thenThrowException() {
        NoSuchElementException exception =
                assertThrows(NoSuchElementException.class, () -> taskService.recommendTask(1, 1));
        assertEquals("No task with required properties found", exception.getMessage());
    }

    @Test
    void givenTooHighPriority_whenRecommendTask_thenThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> taskService.recommendTask(4,
                        20));
        assertEquals("Invalid priority", exception.getMessage());
    }
}
