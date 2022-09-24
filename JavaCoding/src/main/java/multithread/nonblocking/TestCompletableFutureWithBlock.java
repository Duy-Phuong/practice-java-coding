package multithread.nonblocking;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class TestCompletableFutureWithBlock {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(
                "Current Thread Name: "
                        + Thread.currentThread().getName());
        // Using Lambda Expression
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            // Simulate a long-running Job
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Running asynchronously");
                System.out.println(
                        "Current Thread Name: "
                                + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            System.out.println("I'll run in a separate thread than the main thread.");
        });
        // Block and get the result of the Future
        future.get();
    }
}
