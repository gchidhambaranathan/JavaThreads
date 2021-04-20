package com.learn.blockingqueue;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class TestBlockingQueue {
    public static void main(String[] args) {

        BlockingQueue<String> queue = new BlockingQueue<>(5);

        Runnable putTask = () -> {
            IntStream.range(0,10).forEach(value -> {
                try {
                    String item = "item"+ value;
                    queue.add(item);
                    System.out.println("Added "+ item);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        };


        Runnable consumeTask = () -> {
            IntStream.range(0,10).forEach(value -> {
                try {
                    String item = queue.get();
                    System.out.println("Consumed "+ item);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        };

        new Thread(putTask).start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(consumeTask).start();
    }
}
