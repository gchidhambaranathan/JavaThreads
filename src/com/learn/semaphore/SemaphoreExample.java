package com.learn.semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class SemaphoreExample {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        ThreadSensitive threadSensitive = new ThreadSensitive();

        List<Callable<Boolean>> callableList = new ArrayList<>();


        IntStream.range(0,20).forEach(value -> {
            Callable<Boolean> callable = () -> {
                threadSensitive.criticalSection();
                return true;
            };
            callableList.add(callable);
        });

        try {
            executorService.invokeAll(callableList);
        } catch (InterruptedException e) {
            System.out.println("Error invoking thread");
        }

    }
}
