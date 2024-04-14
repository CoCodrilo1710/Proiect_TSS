package services;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import model.Consts;
import model.Status;
import model.Task;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
public class TaskService {
    private final List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getByIndex(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new IllegalArgumentException("Invalid task number");
        }

        return tasks.get(index);
    }

    public void updateTask(int index, Task task) {
        if (index < 0 || index >= tasks.size()) {
            throw new IllegalArgumentException("Invalid task number");
        }

        tasks.set(index, task);
    }

    public void removeTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new IllegalArgumentException("Invalid task number");
        }
        tasks.remove(index);
    }

    public void printTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i).getDescription());
        }
    }

    public void saveToJson() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            File directory = new File("src/main/resources");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            objectMapper.writeValue(new File(Consts.SAVE_FILE_LOCATION), tasks);


            //System.out.println("Task saved to file");

        } catch (StreamWriteException | DatabindException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Task findWhatTaskToDoNext(int numberOfTasks, List<Task> tasks) {

        // numberOfTasks must be between 1 and 20
        if (tasks.isEmpty() || numberOfTasks <= 0 || numberOfTasks > tasks.size()) {
            throw new IllegalArgumentException("Invalid number of tasks or empty list");
        }

        Task highestPriorityShortestEstimateTask = null;

        for (int i = 0; i < numberOfTasks; i++) {
            Task task = tasks.get(i);


            // make sure it's not completed
            if (task.getStatus() != Status.COMPLETE) {

                // if no prior task was selected, select task
                if (highestPriorityShortestEstimateTask == null) {
                    highestPriorityShortestEstimateTask = task;
                } else {
                    // check to see if the task is of higher priority and if tasks have the same priority check time estimate
                    if (task.getPriority().compareTo(highestPriorityShortestEstimateTask.getPriority()) > 0) {
                        highestPriorityShortestEstimateTask = task;
                    } else if (task.getPriority() == highestPriorityShortestEstimateTask.getPriority() &&
                            task.getTimeEstimate() < highestPriorityShortestEstimateTask.getTimeEstimate()) {
                        highestPriorityShortestEstimateTask = task;
                    }
                }
            }
        }

        if (highestPriorityShortestEstimateTask == null) {
            throw new IllegalStateException("No available tasks to do");
        }

        return highestPriorityShortestEstimateTask;
    }
}
