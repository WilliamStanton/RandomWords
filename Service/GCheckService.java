package Service;

import Model.Word;

import java.util.Random;

/**
 * Provides methods for generating and checking strings
 * @param dictionaryService Word Dictionary
 * @author William Stanton
 */
public record GCheckService(DictionaryService dictionaryService) {

    /**
     * Generates String & returns object from dictionary if it exists, otherwise returns null
     * @return word object, otherwise null if not found
     */
    public Word checkString() {
        String randWord = generateString();
        if (dictionaryService.isValid(randWord))
            return new Word(randWord, randWord.length());
        return null;
    }

    /**
     * Generates random String of letters
     * @return random String of letters
     */
    private String generateString() {
        // Initialization
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String randString = "";
        var rand = new Random();

        // Generate random word length
        int length = rand.nextInt(dictionaryService.getMax() - (dictionaryService.getMin() - 1)) + dictionaryService.getMin(); // 1 to length
        // Generate random String of specified length
        for (int i = 0; i < length; i++)
            randString += alphabet.charAt(rand.nextInt(alphabet.length()));

        // Return random String
        return randString;
    }
}
