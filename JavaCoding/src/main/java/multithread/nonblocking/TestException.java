package multithread.nonblocking;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class TestException {
    public void test() {
        Integer age = -1;

        CompletableFuture<Void> maturityFuture = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (age < 0) {
                throw new IllegalArgumentException("Age can not be negative");
            }
            if (age > 18) {
                //                return "Adult";
                System.out.println("Adult");
            } else {
                //                return "Child";
                System.out.println("Child");
            }
        }).thenRun(() -> {
            System.out.println("Running 1...");
        }).exceptionally(ex -> {
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            //            return "Unknown!";
            System.out.println("Unknown");
            return null;
        }).thenRun(() -> {
            System.out.println("Running 2...");
        });

        //        try {
        //            System.out.println("Maturity : " + maturityFuture.get());
        //        } catch (InterruptedException e) {
        //            throw new RuntimeException(e);
        //        } catch (ExecutionException e) {
        //            throw new RuntimeException(e);
        //        }
    }

    public CompletableFuture<Void> executeActionAsync() {
        System.out.println(Thread.currentThread().getName() + " is executing");
        CompletableFuture<Void> f = executeMycustomActionHere();
        for (int i = 0; i < 2; i++) {
            System.out.println("Retry " + i + "...");
            int finalI = i;
            f = f.thenApply(CompletableFuture::completedFuture)
                    .exceptionally(t -> {
                        System.out.println("Exception: " + t.getMessage());
                        System.out.println("Index: " + finalI + "...");
                        return executeMycustomActionHere();
                    })
                    .thenCompose(Function.identity());
        }
        return f;
    }

    public CompletableFuture<Void> executeMycustomActionHere() {
        int age = -1;
        return CompletableFuture.runAsync(() -> {
            try {
//                System.out.println("Start");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("executing my custom action here..." + Thread.currentThread().getName() + "...");
//            if (age < 0) {
//                throw new IllegalArgumentException("Age can not be negative");
//            }
        });
    }

    public CompletableFuture<Void> executeActionAsyncInMain() {
        CompletableFuture<Void> f = executeMycustomActionHere();
        for (int i = 0; i < 2; i++) {
            f = f.exceptionally(t -> executeMycustomActionHere().join());
        }
        return f;
    }

    public void executeActionAsync3() {
        System.out.println(Thread.currentThread().getName() + " is executing");
        CompletableFuture<Void> f = executeMycustomActionHere();
        for (int i = 0; i < 2; i++) {
            System.out.println("Retry " + i + "...");
            int finalI = i;
            //            f = f.thenApply(CompletableFuture::completedFuture)
            f = f.thenApply((value) -> {
                        System.out.println("Run the fist logic here..." + String.valueOf(finalI) + " - " + System.currentTimeMillis());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        return CompletableFuture.completedFuture(value);
                    })
                    .exceptionally(t -> {
                        System.out.println("Exception: " + t.getMessage());
                        System.out.println("Index: " + finalI + "...");
                        if (finalI == 1) {
                            return null;
                        }
                        return executeMycustomActionHere();
                    })
                    .thenCompose(Function.identity());
        }
    }

    public CompletableFuture<Void> executeActionAsync4() {
        CompletableFuture<Void> f=executeMycustomActionHere();
        for(int i=0; i<3; i++) {
            int finalI = i;
            System.out.println(f.isDone());
            f = f.thenApply((value) -> {
                        System.out.println("Run the fist logic here..." + String.valueOf(finalI) + " - " + System.currentTimeMillis());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        return CompletableFuture.completedFuture(value);
                    })
                    .exceptionally(t -> {
                        System.out.println("Exception: " + t.getMessage());
                        System.out.println("Index: " + finalI + "...");
                        if (finalI == 2) {
                            return null;
                        }
                        return executeMycustomActionHere();
                    })
                    .thenCompose(Function.identity());
        }
        return f;
    }
}
