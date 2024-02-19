package Model;

/**
 * Model.Word Model
 */
public class Word implements Comparable<Word> {
    private String word;
    private int length;
    private boolean found;

    public Word(String word, int length) {
        this.word = word;
        this.length = length;
        this.found = false;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    @Override
    public String toString() {
        return word;
    }

    @Override
    public int compareTo(Word o) {
        return this.getWord().compareTo(o.getWord());
    }
}
