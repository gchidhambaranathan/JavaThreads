package com.learn.threadpool;

import com.learn.blockingqueue.BlockingQueue;

import java.util.stream.IntStream;

public class CustomThreadPool {
    private static final int QUEUE_UPPER_BOUND = 100;
    private final BlockingQueue<Runnable> queue = new BlockingQueue(QUEUE_UPPER_BOUND);


    public CustomThreadPool(int numberOfThreads){

        IntStream.range(0, numberOfThreads).forEach(value -> new Thread(new ConsumerThread()).start());
    }


    public void submitTask(Runnable runnable){
        try {
            queue.add(runnable);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class ConsumerThread implements  Runnable {

        @Override
        public void run() {
            while(true){
                try {
                    queue.get().run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
