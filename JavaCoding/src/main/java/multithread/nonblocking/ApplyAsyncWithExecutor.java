package multithread.nonblocking;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ApplyAsyncWithExecutor {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // Use custom executor, it will run every 2 seconds and run in another thread of executor
        ScheduledExecutorService SCHEDULER = Executors.newScheduledThreadPool(1);
        Executor scheduler = runnable -> SCHEDULER.schedule(runnable, Duration.ofMillis(2000).toMillis(), TimeUnit.MILLISECONDS);
        System.out.println(System.currentTimeMillis());
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread().getName() + " Time: " + System.currentTimeMillis());
            return "Future";
        }, scheduler);

//        completableFuture.get();
//        System.out.println(System.currentTimeMillis());

//        completableFuture = completableFuture.thenApplyAsync((s) -> s.concat(" is awesome!"), scheduler);
        completableFuture = completableFuture.thenApplyAsync((s) -> {
            System.out.println(Thread.currentThread().getName() + " Time: " + System.currentTimeMillis());
            return s.concat(" is awesome!");
        }, scheduler);

        System.out.println(completableFuture.get());
        System.out.println(System.currentTimeMillis());
        executorService.shutdown();
        SCHEDULER.shutdown();
    }
}
