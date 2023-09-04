/**
 * The dictionary class holds all of the English words
 * and provides methods to check the generated word against
 * @author Billy Stanton
 */
import java.io.*;
import java.util.*;

public class Dictionary {
    // declare
    private String filename; // file name of word list
    private String[] wordList;

    // initialize
    public Dictionary(String filename) throws FileNotFoundException {
        this.filename = filename;
        setWordList(); // load the words
    }
    
    /**
     * The setWords method creates the wordList
     * @throws FileNotFoundException 
     */
    private void setWordList() throws FileNotFoundException {
        // initialize
        File wordListFile = new File(filename); // word list
        Scanner wordListCounter = new Scanner(wordListFile);
        Scanner wordListReader = new Scanner(wordListFile);

        // get file length
        int c = 0; // count file length
        while (wordListCounter.hasNextLine()) {
            c++;
            wordListCounter.nextLine();
        }
        
        wordList = new String[c]; // array to contain words
        
        // load the words
        int i = 0;
        while(wordListReader.hasNextLine()) {
            wordList[i] = wordListReader.nextLine();
            i++;
        }
    }
    
    /**
     * The getWordList method returns the word list
     * @return the word list array
     */
    public String[] getWordList() {
        return wordList;
    }
    
    /**
     * The isValid method returns whether a word is valid or not
     * @param word  the word to check
     * @return true/false if the word is valid or not
     */
    public boolean isValid(String word) {
        // init
        boolean validWord = false;
        
        // check each word
        for (int i = 0; i < wordList.length; i++) {
            if (wordList[i].equalsIgnoreCase(word)) {
                validWord = true;
                break;
            }     
        }
        
        // true/false if the word is valid or not
        return validWord;
    }
}