package multithread.nonblocking.tutorials;

import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class RunExample2 {
    public static void main(String[] args) {
        // CompletableFuture, by default, uses an Executor created by ForkJoinPool.commonPool()
        CompletionStage<Void> cf =
                CompletableFuture.runAsync(() -> performTask("first stage"));
        cf = cf.thenRun(() -> performTask("second stage"));
        cf = cf.thenRunAsync(() -> performTask("third stage"));
        ((CompletableFuture) cf).join();//waits until task is completed
        System.out.println("main exiting");
    }

    private static void performTask(String stage) {
        System.out.println("---------");
        System.out.printf("stage: %s, time before task: %s, thread: %s%n",
                stage, LocalTime.now(), Thread.currentThread().getName());
        try {
            //simulating long task
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("stage: %s, time after task: %s, thread: %s%n",
                stage, LocalTime.now(), Thread.currentThread().getName());
    }
}
