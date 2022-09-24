package multithread.nonblocking;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class TestCompletableFutureWithoutBlock3 {
    private static final Duration WAIT_BETWEEN = Duration.ofMillis(20);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestException exception = new TestException();
//        exception.test();
//        exception.executeActionAsync();
//        exception.executeActionAsync3();

//        exception.executeActionAsync4();
//        exception.executeActionAsyncInMain();

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
        Thread.sleep(9000);
        System.out.println("Finished");
    }
}
