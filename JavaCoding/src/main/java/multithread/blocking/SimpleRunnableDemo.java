package multithread.blocking;

public class SimpleRunnableDemo {

    public static void main(String[] args)
    {
        System.out.println("Main thread is- "
                + Thread.currentThread().getName());
        Thread t1 = new Thread(new SimpleRunnableDemo().new RunnableImpl());
        t1.start();

//        Main thread is- main
//        Thread-0, executing run() method!
    }

    private class RunnableImpl implements Runnable {

        public void run()
        {
            System.out.println(Thread.currentThread().getName()
                    + ", executing run() method!");
        }
    }
}