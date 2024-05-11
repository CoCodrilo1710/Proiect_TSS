package model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Task {
    private String description;
    private Priority priority;
    private Status status;
    private Integer hourEstimate;

    public Task() {
    }

    public Task(String description, Priority priority, Integer hourEstimate) {
        this.description = description;
        this.priority = priority;
        this.hourEstimate = hourEstimate;
        this.status = Status.NEW;
    }

    @Override
    public String toString() {
        return "Description: " + description + '\n' +
                "Priority: " + priority + '\n' +
                "Status: " + status + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(getDescription(), task.getDescription()) && getPriority() == task.getPriority() &&
                getStatus() == task.getStatus() && Objects.equals(getHourEstimate(), task.getHourEstimate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDescription(), getPriority(), getStatus(), getHourEstimate());
    }

    public Integer getTimeEstimate() {
        return hourEstimate;
    }

}