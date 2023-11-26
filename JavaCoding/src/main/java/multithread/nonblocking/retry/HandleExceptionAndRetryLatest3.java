package multithread.nonblocking.retry;

import multithread.nonblocking.TestExceptionService;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

public class HandleExceptionAndRetryLatest3 {
    private static final Duration WAIT_BETWEEN = Duration.ofMillis(2000);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        int age = -1;

        Supplier<CompletableFuture<Void>> attempter = () -> CompletableFuture.runAsync(() -> {
            System.err.println("=> Download file: Start: " + System.currentTimeMillis() + " at Thread " + Thread.currentThread().getName());
            System.err.println("=> Download file: Executing my custom action here..." + Thread.currentThread().getName() + "...");
            try {
                Thread.sleep(2000);
                System.err.println("=> Download file: End: " + System.currentTimeMillis() + " at Thread " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (age < 0) {
                throw new IllegalArgumentException("Age can not be negative");
            }
//            return "operation()";
        }).thenRun(() -> {
            System.out.println("Send notification: " + Thread.currentThread().getName());
        });

        // Note: cannot use this way, we can use supplier directly like above. It will
        // execute the inner method in the retry
//        CompletableFuture<Void> est = CompletableFuture.runAsync(() -> {
//            System.err.println("executing my custom action here..." + Thread.currentThread().getName() + "...");
//            if (age < 0) {
//                throw new IllegalArgumentException("Age can not be negative");
//            }
//            //            return "operation()";
//        }).thenRun(() -> {
//            System.err.println("Send notification: " + Thread.currentThread().getName());
//        });
//        Supplier<CompletableFuture<Void>> test = () -> est;

        RetriesLatest3.withRetries(attempter, t -> true, 3, WAIT_BETWEEN);

        // Check not blocking main thread
//        for (int i = 0; i < 15; i++) {
//            Thread.sleep(1000);
//            System.out.println("------ Thread: " + Thread.currentThread().getName() + " i: " + i + " Time:" + System.currentTimeMillis());
//        }

        // Keep in mind that we need to wait for the action to complete
        // If you don't wait for the action to complete, the main thread will be finished and no output will be written to console.
        Thread.sleep(20000);
        System.out.println("Finished " + Thread.currentThread().getName() + "...");

        RetriesLatest3.SCHEDULER.shutdown();

//        OUTPUT:
//        executing my custom action here...ForkJoinPool.commonPool-worker-1...
//        Oops! We have an exception - java.lang.IllegalArgumentException: Age can not be negative
//        Attempting to retry: 1 maxAttempts 3 Thread: pool-1-thread-1 Time: 1664208226966
//        executing my custom action here...ForkJoinPool.commonPool-worker-1...
//        Oops! We have an exception - java.lang.IllegalArgumentException: Age can not be negative
//        Attempting to retry: 2 maxAttempts 3 Thread: pool-1-thread-1 Time: 1664208230978
//        executing my custom action here...ForkJoinPool.commonPool-worker-1...
//        Finished main...
//        Oops! We have an exception - java.lang.IllegalArgumentException: Age can not be negative
//        Attempting to retry: 3 maxAttempts 3 Thread: pool-1-thread-1 Time: 1664208234987
//        Finished retrying: exceeded max attempts: pool-1-thread-1
    }
}