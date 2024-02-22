import Service.DictionaryService;
import Service.GCheckService;

import java.io.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Generates Random Strings and checks validity against a dictionary
 * to find all words within a specific range
 * @author William Stanton
 */
public class Main {
    // App Config
    private static final int MIN_LENGTH = 4; // min word length
    private static final int MAX_LENGTH = 8; // max word length
    private static final int THREAD_COUNT = 12; // thread count
    private static final AtomicLong HITS = new AtomicLong(1); // Hits Counter
    private static final AtomicLong TRIES = new AtomicLong(1); // Tries Counter

    /**
     * Main Driver
     */
    public static void main(String[] args) throws FileNotFoundException {
        try {
            // Initialization
            DictionaryService dict = new DictionaryService("words.txt", MIN_LENGTH, MAX_LENGTH);
            int dictLength = dict.getDictionary().length;
            System.out.printf("Starting App:\nCharacters: %d-%d\nDictionary: %d words\n------------------------\n", MIN_LENGTH, MAX_LENGTH, dictLength);

            // Start Threads
            var task = new GCheckService(dict);
            for (int i = 0; i < THREAD_COUNT; i++) {
                new Thread(() -> {
                        while (HITS.get() != dictLength) {
                            var word = task.checkString();
                            TRIES.getAndIncrement();
                            if (word != null) {
                                System.out.printf("%s | %,2d / %,2d - (Attempt %,2d)\n", word, Long.parseLong(HITS.toString()), dictLength, Long.parseLong(TRIES.toString()));
                                HITS.getAndIncrement();
                            }
                        }
                }).start();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
}