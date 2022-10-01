package multithread.nonblocking;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

// Latest version is applied to ubiid
public class HandleExeptionAndRetryLatest {
    private static final Duration WAIT_BETWEEN = Duration.ofMillis(2000);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestExceptionService exception = new TestExceptionService();
        // Best way to handle run async in another thread and retry
        // no exception: run only once
        // exception: run 3 times
//        AtomicInteger age = new AtomicInteger(-1);
//        CompletableFuture<Void> attempter = CompletableFuture.runAsync(() -> {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            System.out.println("executing my custom action here..." + Thread.currentThread().getName() + " Time: " + System.currentTimeMillis());
//            age.set(new Random().nextInt(2));
//            System.out.println("Age: " + age);
//            if (age.get() == 0) {
//                throw new IllegalArgumentException("Age can not be negative");
//            }
//        });

//        attempter.thenRun(() -> {
//            System.out.println("Then run to trigger notification: " + Thread.currentThread().getName());
//        });

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
        RetriesLatest.withRetries(attempter, t -> true, 3, WAIT_BETWEEN);

        // Check not blocking main thread
        for (int i = 0; i < 15; i++) {
            Thread.sleep(1000);
            System.out.println("------ Thread: " + Thread.currentThread().getName() + " i: " + i + " Time:" + System.currentTimeMillis());
        }

        // Keep in mind that we need to wait for the action to complete
        // If you don't wait for the action to complete, the main thread will be finished and no output will be written to console.'
        Thread.sleep(12000);
        System.out.println("Finished " + Thread.currentThread().getName() + "...");

        RetriesLatest.SCHEDULER.shutdown();

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
