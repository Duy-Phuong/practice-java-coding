package multithread.blocking;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class MultithreadTaskExample {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

        List<ScheduledFuture<String>> results = new ArrayList<ScheduledFuture<String>>();
        System.out.println(
                "Current Thread Name: "
                        + Thread.currentThread().getName());
        try {
            for (int i = 1; i <= 5; i++) {
                Task task = new Task("Task-" + i);
                System.out.println(i);
                ScheduledFuture<String> result = executor.schedule(task, i * 2, TimeUnit.SECONDS);
                results.add(result);

                // It still waits for the task to complete and block the main thread, so it isn't good solution
                String data = result.get();
                System.out.println(" --- " + data);

                // Only call three times: limit the number of calls
                if (data.contains("Task-3")) {
                    break;
                }
            }

            executor.shutdown();

//          Bad practice: Spring needs to do it for us

//            executor.shutdown();
//            try {
//                if (!executor.awaitTermination(1, TimeUnit.DAYS)) {
//                    executor.shutdownNow();
//                }
//            } catch (InterruptedException e) {
//                executor.shutdownNow();
//            }

            System.out.println("Completed");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//        Current Thread Name: main
//        1
//        Current Thread Name: pool-1-thread-1 - Current time: 2022-09-24T20:26:58.622
//        --- Task [Task-1] executed on : 2022-09-24T20:26:59.628
//        2
//        Current Thread Name: pool-1-thread-1 - Current time: 2022-09-24T20:27:03.635
//        --- Task [Task-2] executed on : 2022-09-24T20:27:04.640
//        3
//        Current Thread Name: pool-1-thread-2 - Current time: 2022-09-24T20:27:10.641
//        --- Task [Task-3] executed on : 2022-09-24T20:27:11.646
//        Completed