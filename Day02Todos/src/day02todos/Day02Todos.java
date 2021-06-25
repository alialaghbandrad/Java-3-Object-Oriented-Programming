package day02todos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Day02Todos {

    static ArrayList<Todo> todoList = new ArrayList<>();
    static Scanner input = new Scanner(System.in);

    static int inputInt() {
        while (true) {
            try {
                int result = input.nextInt(); // ex InputMismatchException
                input.nextLine(); // consume the leftover newline
                return result;
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input, enter an integer. Try again.");
                input.nextLine(); // consume the invalid input
            }
        }
    }

    static int getMenuChoice() {
        System.out.print("Please make a choice [0-4]:\n"
                + "1. Add a todo\n"
                + "2. List all todos (numbered)\n"
                + "3. Delete a todo\n"
                + "4. Modify a todo\n"
                + "0. Exit\n"
                + "Your choice is: ");
        int choice = inputInt();
        return choice;
    }

    public static void main(String[] args) {
        loadDataFromFile();
        while (true) {
            System.out.println("Current instance count: " + Todo.getInstanceCount());
            int choice = getMenuChoice();
            switch (choice) {
                case 1:
                    addTodo();
                    break;
                case 2:
                    listAllTodosNumbered();
                    break;
                case 3:
                    deleteTodo();
                    break;
                case 4:
                    modifyTodo();
                    break;
                case 0:
                    saveDataToFile();
                    System.out.println("Exiting.");
                    return;
                default:
                    System.out.println("Invalid choice, try again");
            }
            System.out.println();
        }
    }

    private static void addTodo() {
        try {
            System.out.println("Adding a todo.");
            System.out.print("Enter task description: ");
            String task = input.nextLine();
            System.out.print("Enter due Date (yyyy/mm/dd): ");
            String dueDateStr = input.nextLine();
            Date dueDate = Globals.dateFormatScreen.parse(dueDateStr); // ex ParseException
            System.out.print("Enter hours of work (integer): ");
            int hours = input.nextInt(); // ex NumberFormatException
            Todo todo = new Todo(task, dueDate, hours, Todo.TaskStatus.Pending); // ex DataInvalidException
            todoList.add(todo);
            System.out.println("You've created the following todo:");
            System.out.println(todo);
        } catch (ParseException | NumberFormatException ex) {
            System.out.println("Error parsing: " + ex.getMessage());
        } catch (DataInvalidException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private static void listAllTodosNumbered() {
        if (todoList.isEmpty()) {
            System.out.println("No todos found");
            return;
        }
        for (int i = 0; i < todoList.size(); i++) {
            System.out.printf("#%d: %s\n", i + 1, todoList.get(i));
        }
    }

    private static void deleteTodo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void modifyTodo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    final static String DATA_FILE_NAME = "todos.txt";

    static void loadDataFromFile() { // calls Todo(String)
        try (Scanner inputFile = new Scanner(new File(DATA_FILE_NAME))) {
            while (inputFile.hasNextLine()) {
                String line = "";
                try {
                    line = inputFile.nextLine();
                    todoList.add(new Todo(line)); // ex DataInvalidException
                } catch (DataInvalidException ex) {
                    System.out.printf("Error parsing line (%s) ignoring: %s\n", ex.getMessage(), line);
                }
            }
        } catch (FileNotFoundException ex) {
            // ignore, it's okay if the file is not there
        }
    }

    static void saveDataToFile() { // calls todo.toDataString();
        try (PrintWriter outputFile = new PrintWriter(new File(DATA_FILE_NAME))) {
            for (Todo todo : todoList) {
                outputFile.println(todo.toDataString());
            }
        } catch (IOException ex) {
            System.out.println("Error writing data back to file");
        }
    }

}
