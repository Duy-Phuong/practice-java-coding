package multithread.nonblocking;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class TestCompletableFutureSupplyAsync {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(
                "Current Thread Name: "
                        + Thread.currentThread().getName());
        // Using Lambda Expression
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
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
            return "";
        });
        System.out.println("Current Thread Name after run async: " + Thread.currentThread().getName());
        // Block and get the result of the Future
        future.thenRun(() -> {
            System.out.println("Running asynchronously in another thread: " + Thread.currentThread().getName());
        });

        Thread.sleep(2000);
        System.out.println("Done");

//        OUTPUT
//        Current Thread Name: main
//        Current Thread Name after run async: main
//        Running asynchronously
//        Current Thread Name: ForkJoinPool.commonPool-worker-1
//        I'll run in a separated thread than the main thread.
//        Running asynchronously in another thread: ForkJoinPool.commonPool-worker-1
//        Done
    }
}
