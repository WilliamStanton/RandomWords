import java.io.*;
import java.util.*;

/**
 * Generates Random Strings and checks validity against a dictionary
 * @author William Stanton
 */
public class RandomStringValidator {

    /**
     * Main Driver
     */
    public static void main(String[] args) {
        // Initialize stats
        int hits = 0, tries = 0;

        // Initialize & start-up application
        try {
            // Initialize Dictionary
            Dictionary dict = new Dictionary("words.txt");

            // Generate string and check against dict
            while (true) {
                String randWord = generateString();
                if (dict.isValid(randWord)) {
                    hits++;
                    System.out.println("HIT (" + randWord + ") | " + hits + " / " + tries);
                }
                tries++;
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
    
    /**
     * Generates a random String of letters
     * @return random String of letters
     */
    public static String generateString() {
        // Initialization
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String randString = "";
        var rand = new Random();
        
        // Generate random word length
        int length = rand.nextInt(9)+1; // 1-8

        // Generate random String of specified length
        for (int i = 0; i < length; i++)
            randString += alphabet.charAt(rand.nextInt(alphabet.length()));

        // Return random String
        return randString;
    }
}
