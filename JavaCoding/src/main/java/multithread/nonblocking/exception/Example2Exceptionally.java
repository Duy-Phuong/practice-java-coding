package multithread.nonblocking.exception;

import java.util.concurrent.CompletableFuture;

public class Example2Exceptionally {
    public static void main(String[] args) throws Exception {
        System.out.println("-- running CompletableFuture --");
        CompletableFuture<Integer> completableFuture = CompletableFuture
                .supplyAsync(() -> {
                    System.out.println("running task");
                    return 10 / 0;
                }).exceptionally(exception -> {
                    System.err.println("exception: " + exception);
                    return 1;
                });
        Thread.sleep(3000);//let the stages complete
        System.out.println("-- checking exceptions --");
        boolean b = completableFuture.isCompletedExceptionally();
        System.out.println("completedExceptionally: " + b);
        System.out.println("-- getting results --");
        int result = completableFuture.get();
        System.out.println(result);
    }
}
