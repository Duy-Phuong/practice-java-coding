
# Multithread

The order to start:
CompletableFutureCompletedFutureTest

Context: asynchronously called to get file and when the file is available, we will continue to send notification directly.

1. multithread/nonblocking/retry/HandleExceptionAndRetryWithSchedule1.java - Only retry
   - Call `RetriesWithSchedule1` class: from https://github.com/webcompere/completable-future-retry/blob/master/src/main/java/uk/org/webcompere/completablefuture/retry/Retries.java
   - It’s identical to the original source code ⇒ Run 3 times
   2. multithread/nonblocking/retry/HandleExceptionAndRetryWithoutSchedule2.java - retry and execute another action
      - call `RetriesWithoutSchedule2.java`
         - Change type of `CompletableFuture<T> attempter` from `Supplier<CompletableFuture<String>> attempter` to `CompletableFuture<Void> attempter` and call `attempter.thenRun`
         - .exceptionally ⇒ handleAsync((res, ex) with `scheduler`
         - attempter.get().thenApply(CompletableFuture::completedFuture) ⇒ expand
         - Remove `CompletableFuture<T> firstAttempt = attempter.get();`
         
        ```bash
            executing my custom action here...ForkJoinPool.commonPool-worker-1 Time: 1700995235868
            Age: -1
            Oops! We have an exception - java.lang.IllegalArgumentException: Age can not be negative
            Attempting to retry: 1 maxAttempts 3 Thread: pool-1-thread-1 Time: 1700995237880
            Oops! We have an exception - java.lang.IllegalArgumentException: Age can not be negative
            Attempting to retry: 2 maxAttempts 3 Thread: pool-1-thread-1 Time: 1700995239899
            Oops! We have an exception - java.lang.IllegalArgumentException: Age can not be negative
            Attempting to retry: 3 maxAttempts 3 Thread: pool-1-thread-1 Time: 1700995241905
            Finished retrying
            Finished
        ```
      - Uncomment `age.set(new Random().nextInt(2));` to see success case

       ```bash
       executing my custom action here...ForkJoinPool.commonPool-worker-1 Time: 1700996162017
       Age: 1
       Run the fist logic here...1 - 1700996162018
       Then run to trigger notification: ForkJoinPool.commonPool-worker-1
       ```
3. multithread/nonblocking/retry/HandleExeptionAndRetryLatest3.java
   - Call `RetriesLatest3.java`
     - Revert to `Supplier<CompletableFuture<T>> attempter`
     - add log in main thread
     - call `CompletableFuture<T> firstAttempt = attempter.get();`
4. multithread/nonblocking/retry/HandleExeptionAndRetryLatest4.java
   - After refactoring
   - Change to `thenApplyAsync(CompletableFuture::completedFuture)` and `.thenApply(CompletableFuture::completedFuture)`


Without log in main thread

```bash
First retry attempt: main
--- Start first attempt: 1701008071203
=> Download file: Start: 1701008071208 at Thread ForkJoinPool.commonPool-worker-1
=> Download file: Executing my custom action here...ForkJoinPool.commonPool-worker-1...
--- End first attempt: 1701008071208
=> Download file: End: 1701008073209 at Thread ForkJoinPool.commonPool-worker-1
Oops! We have an exception - java.lang.IllegalArgumentException: Age can not be negative
Attempting to retry: 0 maxAttempts 3 Thread: pool-1-thread-1 Time: 1701008075225
=> Download file: Start: 1701008077231 at Thread ForkJoinPool.commonPool-worker-1
=> Download file: Executing my custom action here...ForkJoinPool.commonPool-worker-1...
=> Download file: End: 1701008079238 at Thread ForkJoinPool.commonPool-worker-1
Oops! We have an exception - java.lang.IllegalArgumentException: Age can not be negative
Attempting to retry: 1 maxAttempts 3 Thread: pool-1-thread-1 Time: 1701008081248
Finished main...
=> Download file: Start: 1701008083251 at Thread ForkJoinPool.commonPool-worker-1
=> Download file: Executing my custom action here...ForkJoinPool.commonPool-worker-1...
```

With log in main thread: uncomment this block

```java
        for (int i = 0; i < 15; i++) {
            Thread.sleep(1000);
            System.out.println("------ Thread: " + Thread.currentThread().getName() + " i: " + i + " Time:" + System.currentTimeMillis());
        }
```


Version 4

```bash
=> Download file: Start: 1701009765058 at Thread ForkJoinPool.commonPool-worker-1
=> Download file: Executing my custom action here...ForkJoinPool.commonPool-worker-1...
=> Download file: End: 1701009767065 at Thread ForkJoinPool.commonPool-worker-1
Oops! We have an exception with casejava.lang.IllegalArgumentException: Age can not be negative
attempts to retry: 1 Time: 1701009769078
=> Download file: Start: 1701009771091 at Thread ForkJoinPool.commonPool-worker-1
=> Download file: Executing my custom action here...ForkJoinPool.commonPool-worker-1...
=> Download file: End: 1701009773097 at Thread ForkJoinPool.commonPool-worker-1
Oops! We have an exception with java.lang.IllegalArgumentException: Age can not be negative
attempts to retry: 2 Time: 1701009775104
Finished main...
=> Download file: Start: 1701009777108 at Thread ForkJoinPool.commonPool-worker-1
=> Download file: Executing my custom action here...ForkJoinPool.commonPool-worker-1...
=> Download file: End: 1701009779113 at Thread ForkJoinPool.commonPool-worker-1
Oops! We have an exception with java.lang.IllegalArgumentException: Age can not be negative
attempts to retry: 3 Time: 1701009781120
=> Download file: Start: 1701009783128 at Thread ForkJoinPool.commonPool-worker-1
=> Download file: Executing my custom action here...ForkJoinPool.commonPool-worker-1...
=> Download file: End: 1701009785134 at Thread ForkJoinPool.commonPool-worker-1
Oops! We have an exception with java.lang.IllegalArgumentException: Age can not be negative
Finished retrying: exceeded max attempts: pool-1-thread-1
```