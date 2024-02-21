import Service.Dictionary;

import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Generates Random Strings and checks validity against a dictionary
 * to find all words within a specific range
 * @author William Stanton
 */
public class Main {
    // App Config
    private static final int MIN_LENGTH = 8; // min word length
    private static final int MAX_LENGTH = 8; // max word length
    private static final int THREAD_COUNT = 24; // thread count

    /**
     * Main Driver
     */
    public static void main(String[] args) throws FileNotFoundException {
        try {
            // Initialization
            Dictionary dict = new Dictionary("words.txt", MIN_LENGTH, MAX_LENGTH);
            System.out.printf("Starting App:\nCharacters: %d-%d\nDictionary: %d words\n------------------------\n", MIN_LENGTH, MAX_LENGTH, dict.getDictionary().length);

            // Start Threads
            Task task = new Task(dict);
            for (int i = 0; i < THREAD_COUNT; i++) {
                new Threads(String.valueOf(i), task).start();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
}

/**
 * Threading System
 */
class Threads extends Thread {
    // Fields
    private Thread t;
    private final String threadName;
    private final Task task;
    private final int dictLength;
    private static final AtomicLong hits = new AtomicLong(1);
    private static final AtomicLong tries = new AtomicLong(1);

    public Threads(String threadName, Task task) {
        this.threadName = threadName;
        this.task = task;
        this.dictLength = task.getDictionary().getDictionary().length;
    }

    /**
     * Run thread
     */
    public void run() {
        while (hits.get() != dictLength) {
            var word = task.app();
            tries.getAndIncrement();
            if (word != null) {
                System.out.printf("%s | %,2d / %,2d - (Attempt %,2d)\n", word, Long.parseLong(hits.toString()), dictLength, Long.parseLong(tries.toString()));
                hits.getAndIncrement();
            }
        }
    }

    /**
     * Start thread
     */
    public void start() {
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }
}