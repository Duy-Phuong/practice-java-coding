package multithread.blocking;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MultithreadTaskImprovementExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

        List<ScheduledFuture<String>> results = new ArrayList<ScheduledFuture<String>>();
        System.out.println(
                "Current Thread Name: "
                        + Thread.currentThread().getName());
        System.out.println(LocalDateTime.now());
        for (int i = 1; i <= 5; i++)
        {
            Task task = new Task("Task-" + i);
            System.out.println(i);
//            Run every 2 seconds
            ScheduledFuture<String> result = executor.schedule(task, 2, TimeUnit.SECONDS);
            results.add(result);

//          Improvement: Add timeout for get
            String data = result.get(5000, TimeUnit.MILLISECONDS);
//            System.out.println(" --- " + data);
        }
        System.out.println("Current Thread Name: "
                + Thread.currentThread().getName() + " Time: " + LocalDateTime.now());
        System.out.println("========");

        executor.shutdown();

        try {
//            executor.awaitTermination(1, TimeUnit.DAYS);

//            for(ScheduledFuture<String> result : results) {
//                System.out.println(LocalDateTime.now());
//                String data = result.get();
//                System.out.println(" --- " + data);
//                if (data.contains("Task-3")) {
//                    break;
//                }
//            }

            System.out.println("Completed");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
