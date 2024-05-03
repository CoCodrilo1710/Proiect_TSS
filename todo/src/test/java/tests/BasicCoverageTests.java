package tests;

import model.Priority;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.TaskService;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class BasicCoverageTests {

    TaskService taskService;

    private String normalizeLineEndings(String input) {
        return input.replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n");
    }

    @BeforeEach
    void setUp() {
        taskService = new TaskService();
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
}
