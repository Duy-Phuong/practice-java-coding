package multithread.nonblocking;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class HandleExeptionAndRetry {
    private static final Duration WAIT_BETWEEN = Duration.ofMillis(20);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestExceptionService exception = new TestExceptionService();
        // Best way to handle run async in another thread and retry
        // no exception: run only once
        // exception: run 3 times
        int age = -1;
        CompletableFuture<Void> attempter = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("executing my custom action here..." + Thread.currentThread().getName() + "...");
            if (age < 0) {
                throw new IllegalArgumentException("Age can not be negative");
            }
        });
        Retries.withRetries(attempter, t -> true, 3, WAIT_BETWEEN);

        // Keep in mind that we need to wait for the action to complete
        // If you don't wait for the action to complete, the main thread will be finished and no output will be written to console.'
        Thread.sleep(9000);
        System.out.println("Finished");

//        OUTPUT:
//        executing my custom action here...ForkJoinPool.commonPool-worker-1...
//        Attempting to retry: 1 maxAttempts 3 Thread: ForkJoinPool.commonPool-worker-1
//        Attempting to retry: 2 maxAttempts 3 Thread: ForkJoinPool.commonPool-worker-1
//        Attempting to retry: 3 maxAttempts 3 Thread: ForkJoinPool.commonPool-worker-1
//        Finished retrying
//        Finished
    }
}
