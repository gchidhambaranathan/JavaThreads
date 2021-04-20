package com.learn.threadlocal;


/**
 * What ever the value set by two threads on same threadLocalInstance is not affected each other
 */
public class ThreadLocalExample {

    private static ThreadLocal<Integer> integerThreadLocal = ThreadLocal.withInitial(() -> 0);




    public static void main(String[] args) {


        Runnable r1 = () -> {

            System.out.println("Thread 1: Read value before set "+ integerThreadLocal.get());
            integerThreadLocal.set(1);
            System.out.println("Thread 1: Read value after set "+ integerThreadLocal.get());
        };

        Runnable r2 = () -> {

            System.out.println("Thread 2: Read value before set "+ integerThreadLocal.get());
            integerThreadLocal.set(3);
            System.out.println("Thread 2: Read value after set "+ integerThreadLocal.get());
        };

        new Thread(r1).start();
        new Thread(r2).start();

    }
}
