package multithread.blocking;

import java.util.concurrent.*;

/**
 * SimpleScheduledExecutorExample.java
 *
 * This program demonstrates how to schedule a task to execute after
 * a given delay.
 *
 * @author www.codejava.net
 */
public class SimpleScheduledExecutorExample {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler
                = Executors.newSingleThreadScheduledExecutor();

        Runnable task = new Runnable() {
            public void run() {
                System.out.println(System.currentTimeMillis() + " Thread: " + Thread.currentThread().getName());
                System.out.println("Hi!");
            }
        };
        System.out.println(System.currentTimeMillis() + " Thread: " + Thread.currentThread().getName());
        int delay = 2;
        scheduler.schedule(task, delay, TimeUnit.SECONDS);
        scheduler.shutdown();

//        1664024201423 Thread: main
//        1664024203432 Thread: pool-1-thread-1
//        Hi!
    }
}