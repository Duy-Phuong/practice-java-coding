package multithread.nonblocking.retry;

import multithread.nonblocking.TestExceptionService;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

// Version 2 uses the CompletableFuture without supplier. The result will work as expected, but it
// is the foundation for the implementation of HandleExeptionAndRetryLatest
public class HandleExceptionAndRetryWithoutSchedule2 {
    private static final Duration WAIT_BETWEEN = Duration.ofMillis(2000);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestExceptionService exception = new TestExceptionService();
        // Best way to handle run async in another thread and retry
        // no exception: run only once
        // exception: run 3 times
        AtomicInteger age = new AtomicInteger(-1);
        CompletableFuture<Void> attempter = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.err.println("executing my custom action here..." + Thread.currentThread().getName() + " Time: " + System.currentTimeMillis());
//            You can uncomment this to see success case
//            age.set(new Random().nextInt(2));
            System.out.println("Age: " + age);
            if (age.get() < 0) {
                throw new IllegalArgumentException("Age can not be negative");
            }
        });

        attempter.thenRun(() -> {
            System.out.println("Then run to trigger notification: " + Thread.currentThread().getName());
        });
        RetriesWithoutScheduled2.withRetries(attempter, t -> true, 3, WAIT_BETWEEN);

        // Keep in mind that we need to wait for the action to complete
        // If you don't wait for the action to complete, the main thread will be finished and no output will be written to console.
        Thread.sleep(9000);
        System.out.println("Finished");

        RetriesWithoutScheduled2.SCHEDULER.shutdown();
    }
}
