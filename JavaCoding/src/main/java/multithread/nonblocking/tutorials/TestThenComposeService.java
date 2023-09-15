package multithread.nonblocking.tutorials;

import java.util.concurrent.CompletableFuture;

public class TestThenComposeService {
    CompletableFuture<Integer> computeAnother(Integer i){
        return CompletableFuture.supplyAsync(() -> 10 + i);
    }

    public CompletableFuture<Integer> compute(){
        return CompletableFuture.supplyAsync(() -> 10);
    }
}
