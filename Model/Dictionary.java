package Model;

/**
 * Holds the dictionary
 * @author William Stanton
 */
public class Dictionary {
    private Word[] dictionary;

    public Dictionary(Word[] dictionary) {
        this.dictionary = dictionary;
    }

    public Word[] getDictionary() {
        return dictionary;
    }

    public void setDictionary(Word[] dictionary) {
        this.dictionary = dictionary;
    }
}
