import model.Priority;
import model.Status;
import model.Task;
import services.TaskService;

import java.util.Scanner;

public class App {
    private final Scanner scanner = new Scanner(System.in);
    private final TaskService taskService = new TaskService();

    public void prettyPrint(String text) {
        System.out.println("""
                =====================================================
                            """ + text + """

                =====================================================
                            """);
    }

    public void printMenu() {
        prettyPrint("""
                Menu:
                1. Add task
                2. Update task
                3. Remove task
                4. See all tasks
                5. See task
                6. Give next uncompleted and highest priority task
                7. Give next uncompleted and highest priority task with given time estimate
                8. Save to JSON and exit
                """);
    }

    public void printGreeting() {
        prettyPrint("""
                Welcome to the world's most
                    complex to-do app!
                """);
    }

    public void addTask() {
        prettyPrint("Enter task description:");
        String description = scanner.next();

        if (description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }

        prettyPrint("""
                Enter task priority:
                1. LOW
                2. MEDIUM
                3. HIGH
                """);
        Priority priority = Priority.fromLevel(scanner.nextInt());

        prettyPrint("""
                Enter task hour estimate:
                """);
        Integer hours = scanner.nextInt();

        Task task = new Task(description, priority, hours);
        taskService.addTask(task);
    }

    public void updateTask() {
        prettyPrint("Enter task number:");
        int index = scanner.nextInt() - 1;

        Task updated = new Task();

        prettyPrint("""
                Enter task's new priority:
                1. LOW
                2. MEDIUM
                3. HIGH
                """);
        Priority priority = Priority.fromLevel(scanner.nextInt());
        updated.setPriority(priority);

        prettyPrint("""
                Enter task's new status:
                1. NEW
                2. IN PROGRESS
                3. COMPLETED
                """);
        updated.setStatus(Status.fromLevel(scanner.nextInt()));

        prettyPrint("Enter task's new hour estimate:");
        updated.setHourEstimate(scanner.nextInt());

        taskService.updateTask(index, updated);
    }

    public void removeTask() {
        prettyPrint("Enter task number:");
        int index = scanner.nextInt() - 1;

        taskService.removeTask(index);
    }

    public void printTasks() {
        prettyPrint("Tasks:");
        taskService.printTasks();
    }

    public void seeTask() {
        prettyPrint("Enter task number:");
        int index = scanner.nextInt() - 1;

        prettyPrint(taskService.getByIndex(index).toString());
    }


    public void chooseOption(int option) {
        switch (option) {
            case 1 -> addTask();
            case 2 -> updateTask();
            case 3 -> removeTask();
            case 4 -> printTasks();
            case 5 -> seeTask();
            case 6 -> {
                prettyPrint("How many tasks do you have?");
                int taskCount = scanner.nextInt();
                prettyPrint(taskService.findWhatTaskToDoNext(taskCount).toString());
            }
            case 7 -> {
                prettyPrint("How many tasks do you have?");
                int taskCount = scanner.nextInt();
                prettyPrint("How many hours do you want to work?");
                int hours = scanner.nextInt();
                prettyPrint(taskService.recommendTask(taskCount, hours).toString());
            }
            case 8 -> {
                saveToJson();
                prettyPrint("Saved to JSON!");
                prettyPrint("Goodbye!");
                System.exit(0);
            }
        }
    }

    public void saveToJson() {
        taskService.saveToJson();
    }

    public void run() {

        printGreeting();

        int currentOption = 0;

        while (currentOption != 7) {
            printMenu();

            try {
                currentOption = scanner.nextInt();

                chooseOption(currentOption);

                saveToJson();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                currentOption = 0;
            }

        }
    }
}
