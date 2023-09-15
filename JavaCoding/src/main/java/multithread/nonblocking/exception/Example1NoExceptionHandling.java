package multithread.nonblocking.exception;

import java.util.concurrent.CompletableFuture;

public class Example1NoExceptionHandling {
    public static void main(String[] args) throws Exception {
        System.out.println("-- running CompletableFuture --");
        CompletableFuture<Void> completableFuture = CompletableFuture
                .supplyAsync(() -> {
                    System.out.println("running task");
                    return 1 / 0;
                })
                .thenApply(input -> {
                    System.out.println("multiplying by 2");
                    return input * 2;
                })
                .thenAccept(System.out::println);

        Thread.sleep(3000);//let the stages complete
        System.out.println("-- checking exceptions --");
        boolean b = completableFuture.isCompletedExceptionally();
        System.out.println("completedExceptionally: " + b);
        System.out.println("-- calling join --");
        completableFuture.join();
    }
}

//        -- running CompletableFuture --
//        running task
//        -- checking exceptions --
//        completedExceptionally: true
//        -- calling join --
//        Exception in thread "main" java.util.concurrent.CompletionException: java.lang.ArithmeticException: / by zero
//        at java.util.concurrent.CompletableFuture.encodeThrowable(CompletableFuture.java:273)
//        at java.util.concurrent.CompletableFuture.completeThrowable(CompletableFuture.java:280)
//        at java.util.concurrent.CompletableFuture$AsyncSupply.run(CompletableFuture.java:1606)
//        at java.util.concurrent.CompletableFuture$AsyncSupply.exec(CompletableFuture.java:1596)
//        at java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:289)
//        at java.util.concurrent.ForkJoinPool$WorkQueue.runTask(ForkJoinPool.java:1067)
//        at java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1703)
//        at java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:172)
//        Caused by: java.lang.ArithmeticException: / by zero
//        at multithread.nonblocking.exception.Example1NoExceptionHandling.lambda$main$0(Example1NoExceptionHandling.java:11)
//        at java.util.concurrent.CompletableFuture$AsyncSupply.run(CompletableFuture.java:1604)
//        ... 5 more
