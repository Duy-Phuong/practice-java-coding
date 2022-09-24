package multithread.nonblocking;

import java.time.Duration;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

public class TestCompletableFutureWithExceptionAndRetry {
    private static final Duration WAIT_BETWEEN = Duration.ofMillis(20);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestExceptionService exception = new TestExceptionService();

//        Uncomment each line to see the result

//        exception.handleException();
//        Oops! We have an exception: java.lang.IllegalArgumentException: Age can not be negative
//                Unknown
//        Running 2...
//        Finished

//        Uncomment exception in executeMycustomActionHere to see the difference

//        Run 3 time
        exception.executeActionAsync();

//        main is executing
//        Retry 0...
//        Retry 1...
//        executing my custom action here...ForkJoinPool.commonPool-worker-1...
//        Exception: java.lang.IllegalArgumentException: Age can not be negative
//        Index: 0...
//        executing my custom action here...ForkJoinPool.commonPool-worker-2...
//        Exception: java.lang.IllegalArgumentException: Age can not be negative
//        Index: 1...
//        executing my custom action here...ForkJoinPool.commonPool-worker-2...
//        Finished

        // Only run 2 times since we add if in the exceptionally
//        exception.executeActionAsync2();

//        Run 3 times
//        exception.executeActionAsyncUsingJoin();

//        executing my custom actexecuting my custom action here...ForkJoinPool.commonPool-worker-1...
//        executing my custom action here...ForkJoinPool.commonPool-worker-2...
//        executing my custom action here...ForkJoinPool.commonPool-worker-2...
//        Finished

        // Keep in mind that we need to wait for the action to complete
        // If you don't wait for the action to complete, the main thread will be finished and no output will be written to console.'
        Thread.sleep(9000);
        System.out.println("Finished");
    }
}
