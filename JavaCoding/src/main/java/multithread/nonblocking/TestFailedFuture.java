package multithread.nonblocking;

import java.util.concurrent.CompletableFuture;

public class TestFailedFuture {
    public static void main(String[] args) {
        Throwable exception = new RuntimeException("Runtime exception thrown by future");

        // failedFuture only exists in Java 9
//        We get a CompletableFuture completed exceptionally using the failedFuture() method, passing exception as the argument to the method.
//        CompletableFuture<String> completableFutureCompletedExceptionally = CompletableFuture.failedFuture(exception);
//
//        try{
//            completableFutureCompletedExceptionally.join();
//        }catch (Exception ex){
//            System.out.println("Exception Message - " + ex.getMessage());
//        }
    }
}
