package multithread.nonblocking;

import java.time.Duration;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

public class TestCompletableFutureWithExceptionAndRetry {
    private static final Duration WAIT_BETWEEN = Duration.ofMillis(20);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestExceptionService exception = new TestExceptionService();

//        Uncomment each line to see the result:

//        ======== Test Case 1 ========
//        exception.handleException();

//        - OUTPUT:
//        Oops! We have an exception: java.lang.IllegalArgumentException: Age can not be negative
//                Unknown
//        Running 2...
//        Finished

//        Uncomment exception in executeMycustomActionHere to see the difference

//        ======== Test Case 2 ========
//        Run 3 times => need to fix since we only need to run 2 times
//        exception.executeActionAsync();

//        - OUTPUT:
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

//        ======== Test Case 3 ========
//        Trick: Only run 2 times since we add if in the exceptionally to exit the execution in the third time
//        exception.executeActionAsync2();

//        - OUTPUT:
//        main is executing
//        Retry 0...
//        Retry 1...
//        executing my custom action here...ForkJoinPool.commonPool-worker-1...
//        Exception: java.lang.IllegalArgumentException: Age can not be negative
//        Index: 0...
//        executing my custom action here...ForkJoinPool.commonPool-worker-1...
//        Exception: java.lang.IllegalArgumentException: Age can not be negative
//        Index: 1...
//        Finished

//        ======== Test Case 3 ========
//        Still run 3 times
        exception.executeActionAsyncUsingJoin();

//        - OUTPUT:
//        executing my custom action executing my custom action here...ForkJoinPool.commonPool-worker-1...
//        executing my custom action here...ForkJoinPool.commonPool-worker-2...
//        executing my custom action here...ForkJoinPool.commonPool-worker-2...
//        Finished

        // Keep in mind that we need to wait for the action to complete
        // If you don't wait for the action to complete, the main thread will be finished and no output will be written to console.'
        Thread.sleep(9000);
        System.out.println("Finished");
    }
}
