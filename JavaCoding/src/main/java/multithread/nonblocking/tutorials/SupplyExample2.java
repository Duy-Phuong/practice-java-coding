package multithread.nonblocking.tutorials;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

public class SupplyExample2 {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> ThreadLocalRandom.current().nextInt(1, 10))
                .thenApply(Math::sqrt)
                .thenAccept(System.out::println)
                .join();
    }
}
