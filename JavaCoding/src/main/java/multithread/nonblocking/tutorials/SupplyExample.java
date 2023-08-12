package multithread.nonblocking.tutorials;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

public class SupplyExample {
    public static void main(String[] args) {
        CompletableFuture<Integer> cf =
                CompletableFuture.supplyAsync(() -> ThreadLocalRandom.current().nextInt(1, 10));
        Integer integer = cf.join();//similar to cf.get()
        System.out.println(integer);
    }
}
