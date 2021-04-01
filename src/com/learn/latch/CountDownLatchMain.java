package com.learn.latch;


import java.util.concurrent.CountDownLatch;


public class CountDownLatchMain {



    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        WorkerThread1 workerThread1 = new WorkerThread1(countDownLatch);
        WorkerThread2 workerThread2 = new WorkerThread2(countDownLatch);
        WorkerThread3 workerThread3 = new WorkerThread3(countDownLatch);
        MainThread mainThread = new MainThread(countDownLatch);
        new Thread(mainThread).start();
        new Thread(workerThread1).start();
        new Thread(workerThread2).start();
        new Thread(workerThread3).start();


    }

}


class WorkerThread1 implements Runnable {
    private CountDownLatch countDownLatch;
    public WorkerThread1(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }
    @Override
    public void run() {
        System.out.println("Running WorkerThread1");
        this.countDownLatch.countDown();
    }
}

class WorkerThread2 implements Runnable {
    private CountDownLatch countDownLatch;
    public WorkerThread2(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }
    @Override
    public void run() {
        System.out.println("Running WorkerThread2");
        this.countDownLatch.countDown();
    }
}


class WorkerThread3 implements Runnable {
    private CountDownLatch countDownLatch;
    public WorkerThread3(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }
    @Override
    public void run() {
        System.out.println("Running WorkerThread3");
        this.countDownLatch.countDown();
    }
}

class MainThread implements Runnable {

    private CountDownLatch countDownLatch;
    public MainThread(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }
    @Override
    public void run() {
        System.out.println("Waiting Main thread");
        try {
            this.countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Running Main thread");
    }
}
