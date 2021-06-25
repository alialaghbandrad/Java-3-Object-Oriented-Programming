package day01datafromfile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Day01DataFromFile {

    static final String DATA_FILE_NAME = "input.txt";
    
    static void readDataFromFile() {
        try (Scanner fileInput = new Scanner(new File(DATA_FILE_NAME))) {
            while (fileInput.hasNextLine()) {
                String line = fileInput.nextLine();
                try {
                    double val = Double.parseDouble(line); // exception NumberFormatException
                    numsList.add(val);
                } catch (NumberFormatException ex) {
                    namesList.add(line);
                }
            }
        } catch (IOException ex) {
            System.out.println("Error reading file: " + ex.getMessage());
        }
    }
    
    static ArrayList<String> namesList = new ArrayList<>();
    static ArrayList<Double> numsList = new ArrayList<>();
    
    public static void main(String[] args) {
        readDataFromFile();
        Collections.sort(namesList);
        Collections.sort(numsList);
        System.out.println("Names: " + String.join(", ", namesList));
        System.out.println("Numbers: " + numsList.toString()); // FIXME
        
        System.out.println("done");
    }
    
}
