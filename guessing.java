import java.util.Scanner;
import java.lang.Math;

public class guessing {
    public static void main(String args[]) {
        Scanner userInput = new Scanner(System.in);

        System.out.println("Enter a number from 1-255");
        int number = userInput.nextInt();



        guess(number); // Call the guess method with user input
    }

    public static int[][] guess(int number) {
        Scanner userInput = new Scanner(System.in);

        // Calculate the number of rounds needed based on the logarithm
        
        // Array to store user input for each set
        int roundUp = (int) Math.round(Math.log(number) / Math.log(2));

        // Create a 2D array to store groups of numbers based on binary representation
        int groups[][] = new int[roundUp][128];

        // Populate the groups array
        for (int i = 0; i < roundUp; i++) {
            int count = 0;
            for (int j = 1; j <= number; j++) {
                // Check if the jth number belongs to the current group based on binary representation
                if ((j / (int) Math.pow(2, i)) % 2 != 0) {
                    groups[i][count++] = j;
                }
            }
        }
        int binaryNum1[] = new int[roundUp];

        // Loop through each group and ask the user if their number is in the set
        for (int i = 0; i < roundUp; i++) {
            int count = 0;
            for (int j = 1; j <= number; j++) {
                if ((j / (int) Math.pow(2, i)) % 2 != 0) {
                    System.out.print(groups[i][count++] + " ");
                }
            }
            System.out.println("");
            System.out.println("Is your number in this set? (Enter 1 for yes, 0 for no)");
            binaryNum1[i] = userInput.nextInt();
            System.out.println(binaryNum1[i] + " ");
        }

        int total = 0;
        // Calculate the guessed number based on user input
        System.out.println("");
        for (int i = 0; i < roundUp; i++) {
            if (binaryNum1[i] == 1) {
                total += (int) Math.pow(2, i);
            }
        }

        System.out.println(total); // Print the guessed number

        System.out.println("Is this your number? (1 for yes, 0 for no)");
        int answer = userInput.nextInt();
        if (answer == 1) {
            System.out.println("Ok, that's great!");
        } else {
            System.out.println("What was your number?");
            int userNumber = userInput.nextInt();
            search(groups, roundUp, number, userNumber);
        }
        return groups;
    }

    // Search for the user's number in the sets
    public static void search(int[][] groups, int roundUp, int number, int userNumber) {
        for (int i = 0; i < roundUp; i++) {
            for (int j = 1; j <= number; j++) {
                if (groups[i][j] == userNumber) {
                    System.out.print("Found your number in the set: " + (i + 1));
                }else{
                    System.out.println("Your number was not in the sets");
                }
            }
            System.out.println("");
        }
    }
}
