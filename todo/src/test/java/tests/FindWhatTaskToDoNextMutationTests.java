package tests;

import model.Priority;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.TaskService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FindWhatTaskToDoNextMutationTests {
    TaskService taskService;

    @BeforeEach
    public void setUp() {
        taskService = new TaskService();
    }

    @Test
    void givenBestTasksWIthSameTimeEstimate_whenFindWhatTaskToDoNext_thenReturnFirst() {
        taskService.addTask(new Task("Task 1", Priority.HIGH, 3));
        taskService.addTask(new Task("Task 2", Priority.HIGH, 3));

        int numberOfTasks = 2;

        Task returnedTask = taskService.findWhatTaskToDoNext(numberOfTasks);
        Task expectedTask = taskService.getByIndex(0);

        assertEquals(expectedTask, returnedTask);
    }

    @Test
    void givenMaximumNumberOfTasks_whenFindWhatTaskToDoNext_thenReturnTask() {
        taskService.addTask(new Task("Task 1", Priority.HIGH, 3));
        taskService.addTask(new Task("Task 2", Priority.MEDIUM, 2));
        taskService.addTask(new Task("Task 3", Priority.LOW, 1));
        taskService.addTask(new Task("Task 4", Priority.HIGH, 5));
        taskService.addTask(new Task("Task 5", Priority.MEDIUM, 4));
        taskService.addTask(new Task("Task 6", Priority.LOW, 3));
        taskService.addTask(new Task("Task 7", Priority.HIGH, 7));
        taskService.addTask(new Task("Task 8", Priority.MEDIUM, 6));
        taskService.addTask(new Task("Task 9", Priority.LOW, 5));
        taskService.addTask(new Task("Task 10", Priority.HIGH, 9));
        taskService.addTask(new Task("Task 11", Priority.MEDIUM, 8));
        taskService.addTask(new Task("Task 12", Priority.LOW, 7));
        taskService.addTask(new Task("Task 13", Priority.HIGH, 11));
        taskService.addTask(new Task("Task 14", Priority.LOW, 7));
        taskService.addTask(new Task("Task 15", Priority.LOW, 9));
        taskService.addTask(new Task("Task 16", Priority.HIGH, 13));
        taskService.addTask(new Task("Task 17", Priority.MEDIUM, 12));
        taskService.addTask(new Task("Task 18", Priority.LOW, 11));
        taskService.addTask(new Task("Task 19", Priority.HIGH, 15));
        taskService.addTask(new Task("Task 20", Priority.MEDIUM, 14));

        Task expectedResult = taskService.getByIndex(0);

        assertEquals(expectedResult, taskService.findWhatTaskToDoNext(20));
    }
}
