package tests;

import model.Priority;
import model.Task;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import services.TaskService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecommendTaskEquivalencePartitioning {
    TaskService taskService;

    @BeforeEach
    public void setUp() {
        taskService = new TaskService();
    }

    @Test
    public void givenValidIndex_whenRecommendTask_thenRecommendTask() {

    }

}
