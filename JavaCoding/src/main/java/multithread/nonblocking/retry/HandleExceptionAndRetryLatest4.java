package multithread.nonblocking.retry;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

// Latest version is applied to ubiid
public class HandleExceptionAndRetryLatest4 {
    private static final Duration WAIT_BETWEEN = Duration.ofMillis(2000);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        int age = -1;

        Supplier<CompletableFuture<Void>> attempter = () -> CompletableFuture.runAsync(() -> {
            System.out.println("=> Download file: Start: " + System.currentTimeMillis() + " at Thread " + Thread.currentThread().getName());
            System.out.println("=> Download file: Executing my custom action here..." + Thread.currentThread().getName() + "...");
            try {
                Thread.sleep(2000);
                System.out.println("=> Download file: End: " + System.currentTimeMillis() + " at Thread " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (age < 0) {
                throw new IllegalArgumentException("Age can not be negative");
            }
        }).thenRun(() -> {
            System.out.println("Send notification: " + Thread.currentThread().getName());
        });


        RetriesLatest4.withRetries(attempter, t -> true, 3, WAIT_BETWEEN);

        // Keep in mind that we need to wait for the action to complete
        // If you don't wait for the action to complete, the main thread will be finished and no output will be written to console.
        Thread.sleep(20000);
        System.out.println("Finished " + Thread.currentThread().getName() + "...");

        RetriesLatest4.SCHEDULER.shutdown();
    }
}