package multithread.nonblocking;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class RetriesLatest {

    public static final ScheduledExecutorService SCHEDULER = Executors.newScheduledThreadPool(1);

    public static <T> void withRetries(Supplier<CompletableFuture<T>> attempter,
            Predicate<Throwable> shouldRetry,
            int attempts, Duration waitBetween) {
        // retries run via the scheduler
        // The first time no need to wait so you can set the wait between to 0ms
        Executor scheduler = runnable -> SCHEDULER.schedule(runnable, waitBetween.toMillis(), TimeUnit.MILLISECONDS);
        System.out.println("First retry attempt: " + Thread.currentThread().getName());
        System.err.println("--- Start first attempt: " + System.currentTimeMillis());
        CompletableFuture<T> firstAttempt = attempter.get();
        System.err.println("--- End first attempt: " + System.currentTimeMillis());
        flatten(firstAttempt.thenApply((value) -> {
                    System.out.println("Run the fist logic here...: " + Thread.currentThread().getName() + String.valueOf(
                            1) + " - " + System.currentTimeMillis());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return CompletableFuture.completedFuture(value);
                })
                .handleAsync((res, ex) -> {
                    if (ex != null) {
                        System.out.println("Oops! We have an exception - " + ex.getMessage());
                        retry(attempter, 1, ex.getMessage(), shouldRetry, attempts, scheduler);
                        return null;
                    }
                    return res;
                }, scheduler));
    }

    private static <T> CompletableFuture<T> retry(Supplier<CompletableFuture<T>> attempter,
            int attemptsSoFar,
            String throwable,
            Predicate<Throwable> shouldRetry,
            int maxAttempts,
            Executor scheduler) {
        System.out.println(
                "Attempting to retry: " + attemptsSoFar + " maxAttempts " + maxAttempts + " Thread: " + Thread.currentThread()
                        .getName() + " Time: " + System.currentTimeMillis());
        // cannot retry if we're at the max attempts or the predicate doesn't like the error
        int nextAttempt = attemptsSoFar + 1;
        if (nextAttempt > maxAttempts) {
            //            return CompletableFuture.failedFuture(throwable);
            System.out.println("Finished retrying: exceeded max attempts: " + Thread.currentThread().getName());
            return null;
        }

        return flatten(flatten(CompletableFuture.supplyAsync(attempter, scheduler))
                .thenApplyAsync((value) -> {
                    System.out.println("Run the second logic here..." + String.valueOf(
                            attemptsSoFar) + " - " + System.currentTimeMillis());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return CompletableFuture.completedFuture(value);
                })
                .handleAsync((res, ex) -> {
                            if (ex != null) {
                                System.out.println("Oops! We have an exception - " + ex.getMessage());
                                retry(attempter, nextAttempt, ex.getMessage(), shouldRetry, maxAttempts, scheduler);
                                return null;
                            }
                            return res;
                        }
                        , scheduler));
    }

    private static <T> CompletableFuture<T> flatten(CompletableFuture<CompletableFuture<T>> completableCompletable) {
        return completableCompletable.thenCompose(Function.identity());
    }
}