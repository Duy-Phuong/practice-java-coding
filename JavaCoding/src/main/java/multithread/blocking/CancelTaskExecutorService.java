package multithread.blocking;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class CancelTaskExecutorService {
    public static void main(String[] args) throws InterruptedException, ExecutionException
    {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime afterOneMinute = now.plusMinutes(1);

        Duration duration = Duration.between(now, afterOneMinute);
        long delay = Math.abs(duration.toMillis());

        System.out.println("Task scheduled at : "+ LocalDateTime.now());

        ScheduledFuture<String> result = executor.schedule(new NewTask("Task-1"), delay, TimeUnit.MILLISECONDS);

        System.out.println("Task is done : " + result.isDone());

        if(result.isDone() == false)
        {
            System.out.println("====Cancelling the task====");
            result.cancel(false);
        }

        System.out.println("Task is cancelled : " + result.isCancelled());
        System.out.println("Task is done : " + result.isDone());

        executor.shutdown();

//        Task scheduled at : 2022-09-24T20:06:40.035
//        Task is done : false
//            ====Cancelling the task====
//        Task is cancelled : true
//        Task is done : true
    }
}

class NewTask implements Callable<String>
{
    private final String name;

    public NewTask(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        System.out.println("Task [" + name + "] executed on : " + LocalDateTime.now().toString());
        return "Task [" + name + "] is SUCCESS !!";
    }
}