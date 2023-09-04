/**
 * The RandomStringValidator program generates random words infinitely
 * until a valid word has been found
 * @author Billy Stanton
 */

import java.io.*;
import java.util.*;

public class RandomStringValidator {

    public static void main(String[] args) throws FileNotFoundException {
        // import file with words
        Dictionary dict = new Dictionary("words.txt");
        
        // generate random words and check them against dict
        int hits = 0;
        while (true) {
            String randWord = generateString();
            if (dict.isValid(randWord)) {
                hits++;
                System.out.println("HIT " + hits + " : " + randWord);
            }
        }
    }
    
    /**
     * The generate word class generates a random string of letters
     * @return a random String
     */
    public static String generateString() {
        // init
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String randString = "";
        Random randGenerator = new Random();
        
        // generate random length
        int length = randGenerator.nextInt(7)+4;
        
        // generate random string of letters of length
        for (int i = 0; i < length; i++) {
            randString += alphabet.charAt(randGenerator.nextInt(alphabet.length()));
        }
        
        return randString;
    }
}
