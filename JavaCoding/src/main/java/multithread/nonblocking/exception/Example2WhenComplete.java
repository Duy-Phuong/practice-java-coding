package multithread.nonblocking.exception;

import java.util.concurrent.CompletableFuture;

public class Example2WhenComplete {
    public static void main(String[] args) throws Exception {
        runTasks(2);
        runTasks(0);
    }

    private static void runTasks(int i) {
        System.out.printf("-- input: %s --%n", i);
        CompletableFuture
                .supplyAsync(() -> {
                    return 16 / i;
                })
                .whenComplete((input, exception) -> {
                    if (exception != null) {
                        System.out.println("exception occurs");
                        System.err.println(exception);
                    } else {
                        System.out.println("no exception, got result: " + input);
                    }
                })
                .exceptionally(throwable ->
                {
                    System.out.println("recovering in exceptionally: " + throwable);
                    return 1;
                })
                .thenApply(input -> input * 3)
                .thenAccept(System.out::println);

    }
}