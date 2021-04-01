package com.learn.barrier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.IntStream;

/**
 * The idea of this sample java code to explain
 * concept of cyclic barrier . We are waiting 2 parties to fill 3 random
 * The second will add three random numbers in the list
 * And third party will will sum both output of party 1 and party 2;
 */
public class CyclicBarrierDemo {

    private static final int NUMBER_OF_PARTIES = 3;

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER_OF_PARTIES);
        Random random = new Random();
        List<Integer> randomNumbers = Collections.synchronizedList(new ArrayList<>(6));

        Runnable addRandomNumbers1 = () -> {
            IntStream.range(0, 3).forEach(value -> randomNumbers.add(random.nextInt()));

            System.out.println("Thread 1 is barrier is broken " + cyclicBarrier.isBroken());
            try {
                System.out.println("Thread 1 is reaches");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

        };

        Runnable addRandomNumbers2 = () -> {
            IntStream.range(0, 3).forEach(value -> {
                randomNumbers.add(random.nextInt());
            });

            System.out.println("Thread 2is barrier is broken " + cyclicBarrier.isBroken());
            try {
                System.out.println("Thread 2 is reaches");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

        };


        Runnable aggreGatorThread = () -> {

            try {
                System.out.println("Aggregator is reaches");
                System.out.println("Aggregator thread is barrier is broken before sum " + cyclicBarrier.isBroken());
                cyclicBarrier.await();
                int sum = randomNumbers.stream().reduce(0, Integer::sum);

                System.out.println("Total sum on random numbers " + sum);

                cyclicBarrier.reset();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

        };

        new Thread(aggreGatorThread).start();
        new Thread(addRandomNumbers1).start();
        new Thread(addRandomNumbers2).start();
    }

}
