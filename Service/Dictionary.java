package Service;

import Model.Word;

import java.io.*;
import java.util.*;

/**
 * Holds the English word Service.Dictionary and provides a method to check word validity
 * @author William Stanton
 */
public class Dictionary {
    // Declare Variables
    private final String filename;
    private final int min, max;
    private Word[] dictionary;

    // Initialize Variables
    public Dictionary(String filename, int min, int max) throws FileNotFoundException {
        this.filename = filename;
        this.min = min;
        this.max = max;
        initDictionary();
    }
    
    /**
     * Initializes Service.Dictionary
     */
    private void initDictionary() throws FileNotFoundException {
        // Initialize Variables
        var file = new File(filename); // word list
        var reader = new Scanner(file);

        // Count dictionary file length
        int c = 0;
        while (reader.hasNextLine()) {
            String word = reader.nextLine();
            if (wordLength(word))
                c++;
        }

        // Initialize array with file length
        dictionary = new Word[c]; // array to contain words
        
        // Re-initialize reader & load the dictionary
        reader = new Scanner(file);
        int i = 0;
        while(reader.hasNextLine()) {
            String word = reader.nextLine();
            if (wordLength(word)) {
                dictionary[i] = new Word(word, word.length());
                i++;
            }
        }
    }

    private boolean wordLength(String word) {
        return word.length() >= min && word.length() <= max;
    }

    /**
     * Checks if a word exists in the dictionary and has not yet been found
     * @param word word to check
     * @return true/false
     */
    public boolean isValid(String word) {
        // Find index of word
        int index = Arrays.binarySearch(dictionary, new Word(word, word.length()));

        // Return true only if word exists and isn't yet found
        if (index >= 0 && !dictionary[index].isFound()) {
            dictionary[index].setFound(true); // mark word found
            return true;
        }
        else
            return false;
    }
    
    /**
     * Returns Service.Dictionary
     * @return dictionary array
     */
    public Word[] getDictionary() {
        return dictionary;
    }
}