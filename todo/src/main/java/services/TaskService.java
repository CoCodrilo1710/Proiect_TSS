package services;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import model.Task;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TaskService {
    private final String SAVE_FILE_LOCATION = "src/main/resources/tasks.json";

    @Getter
    private List<Task> tasks;

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
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i).getDescription());
        }
    }

    public void saveToJson() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(new File(SAVE_FILE_LOCATION), tasks);
        } catch (StreamWriteException | DatabindException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
