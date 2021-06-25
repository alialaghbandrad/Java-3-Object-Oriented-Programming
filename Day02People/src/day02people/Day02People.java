package day02people;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day02People {

    static ArrayList<Person> people = new ArrayList<>();

    static final String DATA_FILE_NAME = "people.txt";
    
    private static void readDataFromFile() {
        try (Scanner fileInput = new Scanner(new File(DATA_FILE_NAME))) {
            while (fileInput.hasNextLine()) {
                String line = fileInput.nextLine();
                try {
                    String [] data = line.split(";");
                    if (data.length != 2) {
                        //System.out.println("Invalid number of fields in line, skipping.");
                        //continue;
                        throw new InvalidDataException("Every line must have 2 fields");
                    }
                    String name = data[0];
                    int age = Integer.parseInt(data[1]); // ex NumberFormatException
                    Person person = new Person(name, age);
                    people.add(person);
                } catch (NumberFormatException ex) {
                    System.out.println("Error parsing integer, skipping the invalid line");
                    System.out.println(">> " + line);
                } catch (InvalidDataException ex) {
                    System.out.println("Error in data, skippnig line: " + ex.getMessage());
                    System.out.println(">> " + line);
                }
            }
        } catch (IOException ex) {
            System.out.println("Error reading file: " + ex.getMessage());
        }
    }
    
    private static void printDataToScreen() {
        for (Person p : people) {
            System.out.println(p); // toString();
        }
    }
    
    public static void main(String[] args) {
        readDataFromFile();
        printDataToScreen();
    }
    
}
