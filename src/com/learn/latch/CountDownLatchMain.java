package com.learn.latch;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * The main thread wait for three threads to finish it activity
 * and do his job
 */
public class CountDownLatchMain {



    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(3);


        Runnable mainThread = () -> {
            System.out.println("Main Thread waiting for other "+ countDownLatch.getCount() + " threads to finish");
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                System.out.println("Error waiting main thread");
            }

            System.out.println("The worker thread count reaches "+ countDownLatch.getCount() + " so executing main thread");
        };

        Runnable workerThread1 = () -> {
            System.out.println("Running WorkerThread1");
            countDownLatch.countDown();
        };

        Runnable workerThread2 = () -> {
            System.out.println("Running WorkerThread2");
            countDownLatch.countDown();
        };


        Runnable workerThread3 = () -> {
            System.out.println("Running WorkerThread3");
            countDownLatch.countDown();
        };



        // Main thread starts and wait for other three worker thread finishes
        new Thread(mainThread).start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            System.out.println("Error waiting 2 secs...");
        }
        new Thread(workerThread1).start();
        new Thread(workerThread2).start();
        new Thread(workerThread3).start();
    }
}




