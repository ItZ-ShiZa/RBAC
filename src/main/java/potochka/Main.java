package potochka;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int threadCount = 5;
        int taskLength = 20000;

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < threadCount; i++) {
            int threadNumber = i + 1;
            Thread t = new Thread(new Worker(threadNumber, taskLength));
            threads.add(t);
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
