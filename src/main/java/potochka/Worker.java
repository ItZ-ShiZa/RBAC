package potochka;

public class Worker implements Runnable {
    private final int threadNumber;
    private final int taskLength;

    public Worker(int threadNumber, int taskLength) {
        this.threadNumber = threadNumber;
        this.taskLength = taskLength;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        StringBuilder progress = new StringBuilder();

        for (int i = 0; i < taskLength; i++) {
            progress.append("-");

            System.out.printf("Potoc %d (ID %d): [%-" + taskLength + "s]\r",
                    threadNumber,
                    Thread.currentThread().getId(),
                    progress.toString());

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();
        long duration = end - start;

        System.out.printf("Potoc %d (ID %d): [%s] Zavershen za %d ms%n",
                threadNumber,
                Thread.currentThread().getId(),
                progress.toString(),
                duration);
    }
}
