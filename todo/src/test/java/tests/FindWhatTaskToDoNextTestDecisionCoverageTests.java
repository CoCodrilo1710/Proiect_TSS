package tests;

import model.Priority;
import model.Task;
import model.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.TaskService;

import static org.junit.jupiter.api.Assertions.*;

public class FindWhatTaskToDoNextTestDecisionCoverageTests {
    TaskService taskService;

    @BeforeEach
    public void setUp() {
        taskService = new TaskService();
    }

    @Test
    void givenTasksWithDifferentPriorities_whenFindWhatTaskToDoNext_thenSelectHighestPriorityTask()
    {
        // adding two tasks with different priorities to the list of tasks
        taskService.addTask(new Task("Task 1", Priority.HIGH, 3));
        taskService.addTask(new Task("Task 2", Priority.MEDIUM, 2));

        Integer numberOfTasks = 2;

        Task returnedTask = taskService.findWhatTaskToDoNext(numberOfTasks);
        Task expectedTask = taskService.getByIndex(0);

        assertEquals(expectedTask, returnedTask);
    }

    @Test
    void givenTasksWithSamePriorityAndShorterTime_whenFindWhatTaskToDoNext_thenSelectShorterTimeTask()
    {
        // adding two tasks with different priorities to the list of tasks
        taskService.addTask(new Task("Task 1", Priority.HIGH, 3));
        taskService.addTask(new Task("Task 2", Priority.HIGH, 2));

        Integer numberOfTasks = 2;

        Task returnedTask = taskService.findWhatTaskToDoNext(numberOfTasks);
        Task expectedTask = taskService.getByIndex(1);

        assertEquals(expectedTask, returnedTask);
    }

    @Test
    void givenNoAvailableTasks_whenFindWhatTaskToDoNext_thenThrowException()
    {
        // set two tasks
        Task task1 = new Task("Task 1", Priority.HIGH, 3);
        Task task2 = new Task("Task 2", Priority.MEDIUM, 5);

        // set them as complete
        task1.setStatus(Status.COMPLETE);
        task2.setStatus(Status.COMPLETE);

        // add tasks to the list
        taskService.addTask(task1);
        taskService.addTask(task2);

        Integer numberOfTasks = 2;

        // check if method throws IllegalStateException: "No available tasks to do"
        assertThrows(IllegalStateException.class, () -> {
            taskService.findWhatTaskToDoNext(numberOfTasks);
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
    void givenDifferentNumberOfTasksAndTasksListSize_whenFindWhatTaskToDoNext_thenThrowException() {

        taskService.addTask(new Task("Task 1", Priority.HIGH, 3));
        taskService.addTask(new Task("Task 2", Priority.MEDIUM, 2));

        Integer numberOfTasks = 3;

        assertThrows(IllegalArgumentException.class, () -> taskService.findWhatTaskToDoNext(numberOfTasks));
    }

}
