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
        taskService.addTask(new Task("task1", Priority.HIGH, 1));
        taskService.addTask(new Task("task2", Priority.HIGH, 2));
        taskService.addTask(new Task("task3", Priority.HIGH, 3));

        Integer numberOfTasks = 3;
        Task returnedTask = taskService.findWhatTaskToDoNext(numberOfTasks);
        Task expectedTask = taskService.getByIndex(0);

        assertEquals(expectedTask, returnedTask);
    }

    @Test
    void givenTooLowIndex_whenFindWhatTaskToDoNext_thenThrowException(){
        // prepare data
        Integer numberOfTasks = 0;

        assertThrows(IllegalArgumentException.class, () -> taskService.findWhatTaskToDoNext(numberOfTasks));
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

    @Test
    void givenLowestValidIndex_whenFindWhatTaskToDoNext_thenFindTask(){
        // prepare data
        taskService.addTask(new Task("task1", Priority.HIGH, 1));
        Integer numberOfTasks = 1;

        assertThrows(IllegalArgumentException.class, () -> taskService.findWhatTaskToDoNext(0 ));
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