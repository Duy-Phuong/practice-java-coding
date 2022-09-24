package multithread.nonblocking;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Predicate;

public class Retries {

    private static final ScheduledExecutorService SCHEDULER = Executors.newScheduledThreadPool(1);

    public static <T> void withRetries(CompletableFuture<T> attempter,
            Predicate<Throwable> shouldRetry,
            int attempts, Duration waitBetween) {
        // retries run via the scheduler
        Executor scheduler = runnable -> SCHEDULER.schedule(runnable, waitBetween.toMillis(), TimeUnit.MILLISECONDS);


        flatten(attempter.thenApply((value) -> {
                    System.out.println("Run the fist logic here..." + String.valueOf(1) + " - " + System.currentTimeMillis());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return CompletableFuture.completedFuture(value);
                })
                .exceptionally(throwable -> retry(attempter, 1, throwable, shouldRetry, attempts, scheduler)));
    }

    private static <T> CompletableFuture<T> retry(CompletableFuture<T> attempter,
            int attemptsSoFar,
            Throwable throwable,
            Predicate<Throwable> shouldRetry,
            int maxAttempts,
            Executor scheduler) {
        System.out.println(
                "Attempting to retry: " + attemptsSoFar + " maxAttempts " + maxAttempts + " Thread: " + Thread.currentThread()
                        .getName());
        // cannot retry if we're at the max attempts or the predicate doesn't like the error
        int nextAttempt = attemptsSoFar + 1;
        if (nextAttempt > maxAttempts) {
            //            return CompletableFuture.failedFuture(throwable);
            System.out.println("Finished retrying");
            return null;
        }

        return flatten(attempter
                .thenApply((value) -> {
                    System.out.println("Run the fist logic here..." + String.valueOf(attemptsSoFar) + " - " + System.currentTimeMillis());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return CompletableFuture.completedFuture(value);
                })
                .exceptionally(nextThrowable ->
                        retry(attempter, nextAttempt, nextThrowable, shouldRetry, maxAttempts, scheduler)));
    }

    private static <T> CompletableFuture<T> flatten(CompletableFuture<CompletableFuture<T>> completableCompletable) {
        return completableCompletable.thenCompose(Function.identity());
    }
}