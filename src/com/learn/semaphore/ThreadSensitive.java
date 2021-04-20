package com.learn.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class ThreadSensitive {

    private Semaphore semaphore = new Semaphore(3);



    public void criticalSection() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            System.out.println("Error release lock");
        }
        System.out.println("Entering into critical section" + Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Hello critical section.."+ Thread.currentThread().getName());
        System.out.println("Exiting critical section"+ Thread.currentThread().getName());
        semaphore.release();
    }
}
