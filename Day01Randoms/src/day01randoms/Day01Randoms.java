package day01randoms;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Day01Randoms {

    static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
        try {
            System.out.print("How many random integers do you want to generate? ");
            int count = input.nextInt(); // ex IME
            if (count < 0) {
                System.out.println("Error: Number must not be negative");
                System.exit(1);
            }
            System.out.print("Enter minimum: ");
            int min = input.nextInt(); // ex IME
            System.out.print("Enter maximum: ");
            int max = input.nextInt(); // ex IME
            if (min > max) {
                System.out.println("Error: Maximum must be larger or equal to minimum");
                System.exit(1);
            }
            //
            System.out.print("Result: ");
            for (int i = 0; i < count; i++) {
                int val = (int)(Math.random()*(max - min + 1) + min);
                System.out.printf("%s%d", (i == 0 ? "": ", "), val);
            }
            System.out.println();
        } catch (InputMismatchException ex) {
            System.out.println("Error: value must be an integer.");
        }
        
    }
    
}
