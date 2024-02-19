import java.io.*;
import java.util.*;

/**
 * Holds the English word Dictionary and provides a method to check words against
 * @author William Stanton
 */
public class Dictionary {
    // Declare Variables
    private String filename; // file name of word list
    private String[] dictionary;

    // Initialize Variables
    public Dictionary(String filename) throws FileNotFoundException {
        this.filename = filename;
        initDictionary();; // load the words
    }
    
    /**
     * Initializes Dictionary
     */
    private void initDictionary() throws FileNotFoundException {
        // Initialize Variables
        var file = new File(filename); // word list
        var reader = new Scanner(file);

        // Count dictionary file length
        int c = 0;
        while (reader.hasNextLine()) {
            c++;
            reader.nextLine();
        }

        // Initialize array with file length
        dictionary = new String[c]; // array to contain words
        
        // Re-initialize reader & load the dictionary
        reader = new Scanner(file);
        int i = 0;
        while(reader.hasNextLine()) {
            dictionary[i] = reader.nextLine();
            i++;
        }
    }
    
    /**
     * Returns Dictionary
     * @return dictionary array
     */
    public String[] getDictionary() {
        return dictionary;
    }

    /**
     * Returns word validity
     * @param word word to check
     * @return word validity
     */
    public boolean isValid(String word) {
        // Return results
        return Arrays.binarySearch(dictionary, word) >= 0;
    }
}