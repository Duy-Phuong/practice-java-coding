package multithread.blocking;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;

public class Task implements Callable<String>
{
    private final String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        System.out.println(
                "Current Thread Name: "
                        + Thread.currentThread().getName() + " - Current time: " + LocalDateTime.now());
//        Thread.sleep(1000);
        return "Task [" + name + "] executed on : " + LocalDateTime.now().toString();
    }
}