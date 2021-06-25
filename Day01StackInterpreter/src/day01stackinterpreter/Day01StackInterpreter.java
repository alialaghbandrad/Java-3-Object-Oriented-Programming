package day01stackinterpreter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.EmptyStackException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Day01StackInterpreter {

    static Stack<Double> stack = new Stack<>();

    static Scanner input = new Scanner(System.in);
    
    static String chooseProgramFile() {
        System.out.println("These are *.txt files in the current directory");
        File dir = new File(".");
        File[] fileArray = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        });
        for (File f : fileArray) {
            System.out.println(f.getName());
        }
        System.out.print("Enter the name of program file to open: ");
        return input.nextLine();
    }

    static void ensureValue(int one, int two, String msg) {
        if (one != two) {
            throw new IllegalArgumentException(msg);
        }
    }
    
    public static void main(String[] args) {
        File progFile = new File(chooseProgramFile());
        String line = "";
        try (Scanner inputProg = new Scanner(progFile)) {
            while (inputProg.hasNextLine()) {
                line = inputProg.nextLine();
                String[] data = line.split(":");
                double val1, val2, res0;
                switch (data[0]) {
                    case "read":
                        ensureValue(data.length, 2, "Error: read requires text parameter"); // ex
                        System.out.print(data[1] + ":");
                        val1 = input.nextDouble(); // ex InputMismatchException
                        stack.push(val1);
                        break;
                    case "push":
                        ensureValue(data.length, 2, "Error: read requires text parameter");
                        val1 = Double.parseDouble(data[1]); // ex NumberFormatEx
                        stack.push(val1);
                        break;
                    case "pop":
                        ensureValue(data.length, 1, "Error: read requires text parameter");
                        stack.pop(); // ex EmptyStackException
                        break;
                    case "exchange":
                        ensureValue(data.length, 1, "Error: read requires text parameter");
                        val1 = stack.pop(); // EmptyStackException
                        val2 = stack.pop(); // EmptyStackException
                        stack.push(val1);
                        stack.push(val2);
                        break;
                    case "print":
                        ensureValue(data.length, 2, "Error: read requires text parameter");
                        val1 = stack.peek();
                        System.out.printf("%s : %.2f\n", data[1], val1);
                        break;
                    case "add":
                    case "sub":
                    case "mul":
                    case "div":
                        ensureValue(data.length, 1, "Error: read requires text parameter");
                        val1 = stack.pop(); // EmptyStackException
                        val2 = stack.pop(); // EmptyStackException
                        switch (data[0]) {
                            case "add":
                                res0 = val1 + val2;
                                break;
                            case "sub":
                                res0 = val1 - val2;
                                break;
                            case "mul":
                                res0 = val1 * val2;
                                break;
                            case "div":
                                res0 = val1 / val2;
                                break;
                            default:
                                throw new IllegalArgumentException();
                        }
                        stack.push(res0);
                        break;
                    default:
                        System.out.println("Error: invalid command");
                        System.exit(1);
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found: " + ex.getMessage());
        } catch (NumberFormatException ex) {
            System.out.println("Error: number expected");
            System.out.println("In line: " + line);
        } catch (IllegalArgumentException ex) { // 
            System.out.println(ex.getMessage());
        } catch (InputMismatchException ex) { 
            System.out.println("Error: invalid input value");
            System.out.println("In line: " + line);
        } catch (EmptyStackException ex) {
            System.out.println("Error: not enough arguments on the stack");
            System.out.println("In line: " + line);
        }
        
    }

}
