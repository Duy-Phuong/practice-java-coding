package multithread.nonblocking.retry;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Predicate;

public class RetriesWithoutScheduled2 {

    public static final ScheduledExecutorService SCHEDULER = Executors.newScheduledThreadPool(1);

    public static <T> void withRetries(CompletableFuture<T> attempter,
            Predicate<Throwable> shouldRetry,
            int attempts, Duration waitBetween) {
        // retries run via the scheduler
        // The first time no need to wait so you can set the wait between to 0ms
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
                .handleAsync((res, ex) -> {
                    if (ex != null) {
                        System.out.println("Oops! We have an exception - " + ex.getMessage());
                        retry(attempter, 1, ex.getMessage(), shouldRetry, attempts, scheduler);
                        return null;
                    }
                    return res;
                }, scheduler));
    }

    private static <T> CompletableFuture<T> retry(CompletableFuture<T> attempter,
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
            System.out.println("Finished retrying");
            return null;
        }

//        attempter = (CompletableFuture<T>) getFile();

        return flatten(attempter
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

    public static CompletableFuture<Void> getFile() {
        CompletableFuture<Void> attempter = CompletableFuture.runAsync(() -> {
            int age = -1;
            System.out.println("executing my custom action here..." + Thread.currentThread().getName() + " Time: " + System.currentTimeMillis());
//            age = new Random().nextInt(2);
            System.out.println("Age: " + age);
            if (age < 0) {
                throw new IllegalArgumentException("Age can not be negative");
            }
        });
        attempter.thenRun(() -> {
            System.out.println("Notification");
        });
        return attempter;
    }
}