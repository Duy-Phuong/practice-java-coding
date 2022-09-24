package multithread.nonblocking;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class TestCompletableFutureRunAsync {
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
            System.out.println("I'll run in a separated thread than the main thread.");
        });

        // Block and get the result of the Future
//        future.get();

        // Or you can use this way to avoid blocking the main thread
        Thread.sleep(3000);

//        Current Thread Name: main
//        Running asynchronously
//        Current Thread Name: ForkJoinPool.commonPool-worker-1
//        I'll run in a separated thread than the main thread.
    }
}
