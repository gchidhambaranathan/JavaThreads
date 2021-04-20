package com.learn.threadpool;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class CustomThreadPoolExample {

    public static void main(String[] args) {
        CustomThreadPool customThreadPool = new CustomThreadPool(2);

        IntStream.range(0, 10).forEach(value -> {
            Runnable runnable = () -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            };
            customThreadPool.submitTask(runnable);
        });
    }
}
