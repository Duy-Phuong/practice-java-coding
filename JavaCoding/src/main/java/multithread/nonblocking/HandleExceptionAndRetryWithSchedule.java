package multithread.nonblocking;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

// Original version 1 from http://github.com/
public class HandleExceptionAndRetryWithSchedule {
    private static final Duration WAIT_BETWEEN = Duration.ofMillis(2000);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestExceptionService exception = new TestExceptionService();
        // Best way to handle run async in another thread and retry
        // no exception: run only once
        // exception: run 3 times
        int age = -1;

        Supplier<String> operation = () -> "Yes";
        Supplier<CompletableFuture<String>> attempter = () -> CompletableFuture.supplyAsync(() -> {
            System.out.println("executing my custom action here..." + Thread.currentThread().getName() + "...");
            if (age < 0) {
                throw new IllegalArgumentException("Age can not be negative");
            }
            return "operation()";
        });
        RetriesWithSchedule.withRetries(attempter, t -> true, 3, WAIT_BETWEEN);

        // Keep in mind that we need to wait for the action to complete
        // If you don't wait for the action to complete, the main thread will be finished and no output will be written to console.
        Thread.sleep(9000);
        System.out.println("Finished");
        RetriesWithSchedule.SCHEDULER.shutdown();
    }
}
