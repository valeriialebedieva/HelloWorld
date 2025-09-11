package org.example;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ToDoListApp {
    static class Task {
        String description;
        boolean completed;

        Task(String description) {
            this.description = description;
            this.completed = false;
        }

        void markCompleted() {
            completed = true;
        }

        void editDescription(String newDescription) {
            description = newDescription;
        }

        @Override
        public String toString() {
            return (completed ? "[✔] " : "[ ] ") + description;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        while (true) {
            System.out.println("\n==== To-Do List Menu ====");
            System.out.println("1. Add Task");
            System.out.println("2. View All Tasks");
            System.out.println("3. Mark Task as Completed");
            System.out.println("4. Delete a Task");
            System.out.println("5. Edit a Task");
            System.out.println("6. Clear All Tasks");
            System.out.println("7. View Completed Tasks");
            System.out.println("8. Show Task Statistics");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("⚠ Invalid input! Please enter a number.");
                scanner.nextLine(); // clear invalid input
                continue; // restart menu
            }
            scanner.nextLine(); // consume leftover newline

            switch (choice) {
                case 1:
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    tasks.add(new Task(description));
                    System.out.println("Task added!");
                    break;

                case 2:
                    System.out.println("\nYour Tasks:");
                    if (tasks.isEmpty()) {
                        System.out.println("No tasks yet!");
                    } else {
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + ". " + tasks.get(i));
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter task number to mark as completed: ");
                    int taskNum = getValidNumber(scanner, tasks.size());
                    if (taskNum != -1) {
                        tasks.get(taskNum - 1).markCompleted();
                        System.out.println("Task marked as completed!");
                    }
                    break;

                case 4:
                    System.out.print("Enter task number to delete: ");
                    int deleteNum = getValidNumber(scanner, tasks.size());
                    if (deleteNum != -1) {
                        tasks.remove(deleteNum - 1);
                        System.out.println("Task deleted!");
                    }
                    break;

                case 5:
                    System.out.print("Enter task number to edit: ");
                    int editNum = getValidNumber(scanner, tasks.size());
                    if (editNum != -1) {
                        System.out.print("Enter new description: ");
                        String newDesc = scanner.nextLine();
                        tasks.get(editNum - 1).editDescription(newDesc);
                        System.out.println("Task updated!");
                    }
                    break;

                case 6:
                    tasks.clear();
                    System.out.println("All tasks cleared!");
                    break;

                case 7:
                    System.out.println("\nCompleted Tasks:");
                    boolean found = false;
                    for (Task task : tasks) {
                        if (task.completed) {
                            System.out.println(task);
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("No completed tasks yet.");
                    }
                    break;

                case 8:
                    int completedCount = 0;
                    for (Task task : tasks) {
                        if (task.completed) {
                            completedCount++;
                        }
                    }
                    System.out.println("Total tasks: " + tasks.size());
                    System.out.println("Completed tasks: " + completedCount);
                    System.out.println("Pending tasks: " + (tasks.size() - completedCount));
                    break;

                case 9:
                    System.out.println("Exiting... Goodbye!");
                    return;

                default:
                    System.out.println("Invalid option. Try again!");
            }
        }
    }

    // Helper method to safely read task numbers
    private static int getValidNumber(Scanner scanner, int max) {
        int num;
        try {
            num = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("⚠ Invalid input! Please enter a number.");
            scanner.nextLine(); // clear invalid input
            return -1;
        }
        scanner.nextLine(); // consume leftover newline
        if (num <= 0 || num > max) {
            System.out.println("⚠ Invalid task number.");
            return -1;
        }
        return num;
    }
}