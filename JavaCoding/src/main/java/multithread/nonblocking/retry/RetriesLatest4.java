package multithread.nonblocking.retry;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class RetriesLatest4 {

    public static final ScheduledExecutorService SCHEDULER = Executors.newScheduledThreadPool(1);

    public static <T> void withRetries(
            Supplier<CompletableFuture<T>> attempter, Predicate<Throwable> shouldRetry,
            int attempts, Duration waitBetween) {
        int currentRound = 0;
        Executor scheduler = runnable -> SCHEDULER.schedule(runnable, waitBetween.toMillis(), TimeUnit.MILLISECONDS);
        CompletableFuture<T> firstAttempt = attempter.get();
        flatten(
                firstAttempt
                        .thenApply(CompletableFuture::completedFuture)
                        .handleAsync(
                                (res, ex) -> {
                                    if (ex != null) {
                                        System.err.println(
                                                "Oops! We have an exception with case" +
                                                        ex.getMessage());
                                        retry(attempter, currentRound, ex.getMessage(), shouldRetry, attempts, scheduler);
                                        return null;
                                    }
                                    return res;
                                },
                                scheduler));
    }

    private static <T> CompletableFuture<T> retry(
            Supplier<CompletableFuture<T>> attempter,
            int attemptsSoFar,
            String throwable,
            Predicate<Throwable> shouldRetry,
            int maxAttempts,
            Executor scheduler) {
        // cannot retry if we're at the max attempts
        int nextAttempt = attemptsSoFar + 1;
        if (nextAttempt > maxAttempts) {
            System.out.println("Finished retrying: exceeded max attempts: " + Thread.currentThread().getName());
            return null;
        }
        System.out.println("attempts to retry: "
                + (attemptsSoFar + 1) + " Time: " + System.currentTimeMillis());
        return flatten(
                flatten(CompletableFuture.supplyAsync(attempter, scheduler))
                        .thenApplyAsync(CompletableFuture::completedFuture)
                        .handleAsync(
                                (res, ex) -> {
                                    if (ex != null) {
                                        System.err.println(
                                                "Oops! We have an exception with " +
                                                        ex.getMessage());
                                        retry(attempter, nextAttempt, ex.getMessage(), shouldRetry, maxAttempts, scheduler);
                                    }
                                    return res;
                                },
                                scheduler));
    }

    private static <T> CompletableFuture<T> flatten(
            CompletableFuture<CompletableFuture<T>> completableCompletable) {
        return completableCompletable.thenCompose(Function.identity());
    }
}