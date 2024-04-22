package services;

import model.Priority;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {
    TaskService taskService;

    @BeforeEach
    void setUp() {
        taskService = new TaskService();
    }



    @Test
    void givenLowestValidIndex_whenFindWhatTaskToDoNext_thenFindTask(){
        // prepare data
        taskService.addTask(new Task("task1", Priority.HIGH, 1));
        Integer numberOfTasks = 1;

        assertThrows(IllegalArgumentException.class, () -> taskService.findWhatTaskToDoNext(0 ));
    }
}