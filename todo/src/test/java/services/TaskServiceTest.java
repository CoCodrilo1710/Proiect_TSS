package services;

import model.Priority;
import model.Status;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {
    TaskService taskService;

    private String normalizeLineEndings(String input) {
        return input.replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n");
    }

    @BeforeEach
    void setUp() {
        taskService = new TaskService();
    }

    //equivalence partitioning

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

    // Functional testing for findWhatTaskToDoNext
    // Equivalence Partitioning
    @Test
    void givenValidIndex_whenFindWhatTaskToDoNext_thenFindTask() {
        // prepare data
        List<Task> tasks = List.of(
                new Task("Task 1", Priority.HIGH, 3),
                new Task("Task 2", Priority.MEDIUM, 2),
                new Task("Task 3", Priority.LOW, 1)
        );

        Integer numberOfTasks = 3;
        Task returnedTask = taskService.findWhatTaskToDoNext(numberOfTasks, tasks);
        Task expectedTask = tasks.get(0);

        assertEquals(expectedTask, returnedTask);
    }

    @Test
    void givenTooLowIndex_whenFindWhatTaskToDoNext_thenThrowException(){
        // prepare data
        List<Task> tasks = new ArrayList<>();
        Integer numberOfTasks = 0;

        assertThrows(IllegalArgumentException.class, () -> taskService.findWhatTaskToDoNext(numberOfTasks, tasks));
    }

    @Test
    void givenTooHighIndex_whenFindWhatTaskToDoNext_thenThrowException(){
        List<Task> tasks = List.of(
                new Task("Task 1", Priority.HIGH, 3),
                new Task("Task 2", Priority.MEDIUM, 2),
                new Task("Task 3", Priority.LOW, 1),
                new Task("Task 4", Priority.HIGH, 5),
                new Task("Task 5", Priority.MEDIUM, 4),
                new Task("Task 6", Priority.LOW, 3),
                new Task("Task 7", Priority.HIGH, 7),
                new Task("Task 8", Priority.MEDIUM, 6),
                new Task("Task 9", Priority.LOW, 5),
                new Task("Task 10", Priority.HIGH, 9),
                new Task("Task 11", Priority.MEDIUM, 8),
                new Task("Task 12", Priority.LOW, 7),
                new Task("Task 13", Priority.HIGH, 11),
                new Task("Task 14", Priority.MEDIUM, 10),
                new Task("Task 15", Priority.LOW, 9),
                new Task("Task 16", Priority.HIGH, 13),
                new Task("Task 17", Priority.MEDIUM, 12),
                new Task("Task 18", Priority.LOW, 11),
                new Task("Task 19", Priority.HIGH, 15),
                new Task("Task 20", Priority.MEDIUM, 14),
                new Task("Task 21", Priority.LOW, 13)
        );

        Integer numberOfTasks = tasks.size(); // 21

        assertThrows(IllegalArgumentException.class, () -> taskService.findWhatTaskToDoNext(numberOfTasks, tasks));


    }


    // Boundary Value Analysis

    @Test
    void givenLowestValidIndex_whenGetByIndex_thenRetrieveTask() {
        taskService.addTask(new Task("task1", Priority.HIGH, 1));

        assertEquals("task1", taskService.getByIndex(0).getDescription());
    }

    @Test
    void givenHighestValidIndex_whenGetByIndex_thenRetrieveTask() {
        taskService.addTask(new Task("task1", Priority.HIGH, 1));
        taskService.addTask(new Task("task2", Priority.HIGH, 2));
        taskService.addTask(new Task("task3", Priority.HIGH, 3));

        assertEquals("task3", taskService.getByIndex(2).getDescription());
    }

    @Test
    void givenIndexBelowLowest_whenGetByIndex_thenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> taskService.getByIndex(-1));
        assertThrows(IllegalArgumentException.class, () -> taskService.getByIndex(-2));
    }

    @Test
    void givenIndexAboveHighest_whenGetByIndex_thenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> taskService.getByIndex(1));
    }

    // Statement Coverage

    @Test
    void givenValidIndex_whenRemoveTask_thenRemoveTask() {
        taskService.addTask(new Task("task1", Priority.HIGH, 1));
        taskService.addTask(new Task("task2", Priority.HIGH, 2));


        taskService.removeTask(1);
        taskService.removeTask(0);

        assertTrue(taskService.getTasks().isEmpty());
    }

    // Decision Coverage

    @Test
    void givenValidIndex_whenGetByIndex_thenExecuteBothBranches() {
        taskService.addTask(new Task("task1", Priority.HIGH, 1));

        assertNotNull(taskService.getByIndex(0));
        assertThrows(IllegalArgumentException.class, () -> taskService.getByIndex(1));
    }

    // Condition Coverage

    @Test
    void givenNoTasks_whenPrintTasks_thenPrintNoTasksMessage() {
        // No tasks added

        // Redirect System.out to capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        taskService.printTasks();

        String expectedOutput = "No tasks\n";
        assertEquals(normalizeLineEndings(expectedOutput), normalizeLineEndings(outputStream.toString()));

    }

    @Test
    void givenTasksExist_whenPrintTasks_thenPrintTasks() {
        taskService.addTask(new Task("task1", Priority.HIGH, 1));
        taskService.addTask(new Task("task2", Priority.HIGH, 2));

        // Redirect System.out to capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        taskService.printTasks();
        String expectedOutput = "1. task1\n2. task2\n";
        assertEquals(normalizeLineEndings(expectedOutput), normalizeLineEndings(outputStream.toString()));
    }
}