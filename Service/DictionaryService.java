package Service;

import Model.Dictionary;
import Model.Word;

import java.io.*;
import java.util.*;

/**
 * Holds the English word Service.Dictionary and provides a method to check word validity
 * @author William Stanton
 */
public class DictionaryService {
    // Declare Variables
    private final String filename;
    private final int min, max;
    private final Dictionary dictionary;

    // Initialize Variables
    public DictionaryService(String filename, int min, int max) throws FileNotFoundException {
        this.filename = filename;
        this.min = min;
        this.max = max;
        this.dictionary = initDictionary();
    }
    
    /**
     * Initializes Dictionary
     */
    private Dictionary initDictionary() throws FileNotFoundException {
        // Initialize Variables
        Word[] dictionary;
        var file = new File(filename); // word list
        var reader = new Scanner(file);

        // Count dictionary file length
        int c = 0;
        while (reader.hasNextLine()) {
            String word = reader.nextLine();
            if (isLength(word))
                c++;
        }

        // Initialize array with file length
        dictionary = new Word[c]; // array to contain words
        
        // Re-initialize reader & load the dictionary
        reader = new Scanner(file);
        int i = 0;
        while(reader.hasNextLine()) {
            String word = reader.nextLine();
            if (isLength(word)) {
                dictionary[i] = new Word(word, word.length());
                i++;
            }
        }

        // Return dictionary
        return new Dictionary(dictionary);
    }

    /**
     * Gets the dictionary
     * @return array of word objects
     */
    public Word[] getDictionary() {
        return dictionary.getDictionary();
    }

    /**
     * Returns true/false depending upon if a word is the proper length or not for the list
     * @param word word object
     * @return true/false
     */
    private boolean isLength(String word) {
        return word.length() >= min && word.length() <= max;
    }

    /**
     * Checks if a word exists in the dictionary and has not yet been found
     * @param word word to check
     * @return true/false
     */
    public boolean isValid(String word) {
        // Init
        var dict = dictionary.getDictionary();

        // Find index of word
        int index = Arrays.binarySearch(dict, new Word(word, word.length()));

        // Return true only if word exists and isn't yet found
        if (index >= 0 && !dict[index].isFound()) {
            dict[index].setFound(true); // mark word found
            return true;
        }
        else
            return false;
    }

    /**
     * Minimum word characters
     * @return character count
     */
    public int getMin() {
        return min;
    }

    /**
     * Maximum word characters
     * @return character count
     */
    public int getMax() {
        return max;
    }
}