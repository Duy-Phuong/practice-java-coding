package multithread.nonblocking;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class TestThenCompose {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestThenComposeService service = new TestThenComposeService();
//        ThenCompose uses the previous stage as the argument
//        first funct returns 10 => 10 + 10 in the second func
        CompletableFuture<Integer> finalResult = service.compute()
                .thenCompose(service::computeAnother);

//        It will return 20
        System.out.println(finalResult.get());
    }

}
