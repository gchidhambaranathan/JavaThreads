package com.learn.blockingqueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue<T> {

    private Queue<T> queue = new LinkedList<>();
    private int capacity = 0;
    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();


    public BlockingQueue(int capacity){
        this.capacity = capacity;
    }


    public void add(T t) throws InterruptedException {
        lock.lock();
        try {
            if (queue.size() == this.capacity) {
                notFull.await();
            }

            queue.add(t);
            notEmpty.signal();
        }finally {
            lock.unlock();
        }
    }

    public T get() throws InterruptedException {
        lock.lock();
        try {
            if(queue.isEmpty()){
                notEmpty.await();
            }
            T object = queue.remove();
            notFull.signal();
            return object;
        }finally {
            lock.unlock();
        }
   }
}
