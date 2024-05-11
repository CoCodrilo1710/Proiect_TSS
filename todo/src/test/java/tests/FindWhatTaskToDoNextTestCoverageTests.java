package tests;

import model.Priority;
import model.Status;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.TaskService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FindWhatTaskToDoNextTestCoverageTests {
    TaskService taskService;

    @BeforeEach
    public void setUp() {
        taskService = new TaskService();
    }

    @Test
    void givenTasksWithDifferentPriorities_whenFindWhatTaskToDoNext_thenSelectHighestPriorityTask() {
        // adding two tasks with different priorities to the list of tasks
        taskService.addTask(new Task("Task 1", Priority.HIGH, 3));
        taskService.addTask(new Task("Task 2", Priority.MEDIUM, 2));

        Task returnedTask = taskService.findWhatTaskToDoNext(2);
        Task expectedTask = taskService.getByIndex(0);

        assertEquals(expectedTask, returnedTask);
    }

    @Test
    void givenTasksWithSamePriorityAndShorterTime_whenFindWhatTaskToDoNext_thenSelectShorterTimeTask() {
        // adding two tasks with different priorities to the list of tasks
        taskService.addTask(new Task("Task 1", Priority.HIGH, 3));
        taskService.addTask(new Task("Task 2", Priority.HIGH, 2));

        Task returnedTask = taskService.findWhatTaskToDoNext(2);
        Task expectedTask = taskService.getByIndex(1);

        assertEquals(expectedTask, returnedTask);
    }

    @Test
    void givenNoAvailableTasks_whenFindWhatTaskToDoNext_thenThrowException() {
        // set two tasks
        Task task1 = new Task("Task 1", Priority.HIGH, 3);
        Task task2 = new Task("Task 2", Priority.MEDIUM, 5);

        // set them as complete
        task1.setStatus(Status.COMPLETE);
        task2.setStatus(Status.COMPLETE);

        // add tasks to the list
        taskService.addTask(task1);
        taskService.addTask(task2);

        // check if method throws IllegalStateException: "No available tasks to do"
        assertThrows(IllegalStateException.class, () -> {
            taskService.findWhatTaskToDoNext(2);
        }, "No available tasks to do");
    }

    @Test
    void givenTasksWithDifferentPrioritiesAndTimeEstimates_whenFindWhatTaskToDoNext_thenSelectHighestPriorityAndShortestTimeTask() {
        // add tasks to list
        taskService.addTask(new Task("Task 1", Priority.LOW, 3));
        taskService.addTask(new Task("Task 2", Priority.MEDIUM, 2));
        taskService.addTask(new Task("Task 3", Priority.HIGH, 1));

        Task returnedTask = taskService.findWhatTaskToDoNext(3);
        Task expectedTask = taskService.getByIndex(2);

        assertEquals(expectedTask, returnedTask);
    }

    @Test
    void givenEmptyTaskList_whenFindWhatTaskToDoNext_thenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> taskService.findWhatTaskToDoNext(0));
    }

    @Test
    void givenWrongNoOfTasks_whenFindWhatTaskToDoNext_thenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> taskService.findWhatTaskToDoNext(3));
    }

    @Test
    void givenTooHighNoOfTasks_whenFindWhatTaskToDoNext_thenThrowException() {
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
        taskService.addTask(new Task("Task 21", Priority.LOW, 13));

        assertThrows(IllegalArgumentException.class, () -> taskService.findWhatTaskToDoNext(21));
    }
}
