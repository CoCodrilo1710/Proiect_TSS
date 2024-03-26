package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Task {
    private String description;
    private Priority priority;
    private Status status;

    public Task() {}

    public Task(String description, Priority priority) {
        this.description = description;
        this.priority = priority;
        this.status = Status.NEW;
    }

    @Override
    public String toString() {
        return  "Description: " + description + '\n' +
                "Priority: " + priority + '\n' +
                "Status: " + status + '\n';
    }
}