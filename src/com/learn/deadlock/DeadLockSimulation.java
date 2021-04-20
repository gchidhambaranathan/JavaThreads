package com.learn.deadlock;

import java.util.concurrent.TimeUnit;

public class DeadLockSimulation {

    private Object obj1 = new Object();
    private Object obj2 = new Object();


    public static void main(String[] args) {
        DeadLockSimulation deadLockSimulation = new DeadLockSimulation();
        deadLockSimulation.launchThreads();
    }

    private void launchThreads(){
        Runnable r1 = () -> {

            System.out.println("Entering thread 1");

            synchronized (obj1){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj2){
                    System.out.println("Thread1 won two locks");
                }
            }
        };

        Runnable r2 = () -> {

            System.out.println("Entering thread 2");

            synchronized (obj2){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (obj1){
                    System.out.println("Thread2 won two locks");
                }
            }
        };

        new Thread(r1).start();
        new Thread(r2).start();
    }
}
