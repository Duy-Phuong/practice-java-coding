package multithread.nonblocking.tutorials;

import java.util.concurrent.CompletableFuture;

public class ThenCompletableFutureThenCombineExample {
    public static void main(String[] args) {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 10);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 20);

        CompletableFuture<Integer> combinedFuture = future1.thenCombine(future2, (result1, result2) -> result1 + result2);

        // Get the combined result when both futures complete
        combinedFuture.thenAccept(result -> System.out.println("Combined Result: " + result));

        // Wait for the combinedFuture to complete (for demonstration purposes)
        try {
            combinedFuture.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
