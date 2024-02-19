import Service.Dictionary;

import java.io.*;
import java.util.*;

/**
 * Generates Random Strings and checks validity against a dictionary
 * to find all words within a specific range
 * @author William Stanton
 */
public class Main {

    // Random String MAX and MIN length
    public static final int MIN_LENGTH = 4;
    public static final int MAX_LENGTH = 8;

    /**
     * Main Driver
     */
    public static void main(String[] args) {
        // Initialize stats
        int hits = 0, tries = 0;

        // Initialize & start-up application
        try {
            // Initialize App
            Service.Dictionary dict = new Dictionary("words.txt", MIN_LENGTH, MAX_LENGTH);
            int dictLength = dict.getDictionary().length;
            System.out.printf("Starting App:\nCharacters: %d-%d\nService.Dictionary: %d words\n------------------------\n", MIN_LENGTH, MAX_LENGTH, dict.getDictionary().length);

            // Generate string and check against dict till all words found
            while (hits != dictLength) {
                String randWord = generateString();
                if (dict.isValid(randWord)) {
                    hits++;
                    System.out.printf("%s | %d / %d - (Attempt %d)\n", randWord, hits, dictLength, tries);
                }
                tries++;
            }

            // All words found, exit app
            System.out.println("All words found, exiting application");
            System.exit(0);
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
        int length = rand.nextInt(MAX_LENGTH-(MIN_LENGTH-1))+MIN_LENGTH; // 1 to length

        // Generate random String of specified length
        for (int i = 0; i < length; i++)
            randString += alphabet.charAt(rand.nextInt(alphabet.length()));

        // Return random String
        return randString;
    }
}
