package tests;

import model.Priority;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.TaskService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BasicEquivalencePartitioningTests {
    TaskService taskService;

    @BeforeEach
    void setUp() {
        taskService = new TaskService();
    }

    @Test
    void givenValidIndex_whenUpdateTask_thenModifyTasks() {
        //prepare data
        taskService.addTask(new Task("task1", Priority.HIGH, 1));
        taskService.addTask(new Task("task2", Priority.HIGH, 2));
        taskService.addTask(new Task("task3", Priority.HIGH, 3));

        Task updatedTask = new Task("new task1", Priority.LOW, 4);

        //call method
        taskService.updateTask(0, updatedTask);

        //check result
        assertEquals(updatedTask, taskService.getByIndex(0));
    }

    @Test
    void givenNegativeIndex_whenUpdateTask_thenThrowException() {
        //prepare data
        taskService.addTask(new Task("task1", Priority.HIGH, 1));
        taskService.addTask(new Task("task2", Priority.HIGH, 2));
        taskService.addTask(new Task("task3", Priority.HIGH, 3));

        Task updatedTask = new Task("new task1", Priority.LOW, 4);

        //call method
        assertThrows(IllegalArgumentException.class, () -> taskService.updateTask(-1, updatedTask));
    }

    @Test
    void givenTooHighIndex_whenUpdateTask_thenThrowException() {
        //prepare data
        taskService.addTask(new Task("task1", Priority.HIGH, 1));
        taskService.addTask(new Task("task2", Priority.HIGH, 2));
        taskService.addTask(new Task("task3", Priority.HIGH, 3));

        Task updatedTask = new Task("new task1", Priority.LOW, -1);

        //call method
        assertThrows(IllegalArgumentException.class, () -> taskService.updateTask(3, updatedTask));
    }
}
