package multithread.nonblocking;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureCompletedFutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String resultOfTheFuture = "hello-educative";

        CompletableFuture<String> stringCompletableFuture = CompletableFuture.completedFuture(resultOfTheFuture);

        String value = stringCompletableFuture.get();

        System.out.println("Returned value - " + value);
        // Output: Returned value - hello-educative

    }
}
