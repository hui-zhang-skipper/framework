package com.hui.zhang.mq;

import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {

    private final Lock lock;
    private final Condition notFull;
    private final Condition notEmpty;

    private int maxSize;
    private LinkedList<Date> storage;

    public Buffer(int size) {
        lock = new ReentrantLock();
        notFull = lock.newCondition();
        notEmpty = lock.newCondition();
        maxSize = size;
        storage = new LinkedList<>();

    }

    public void put() {
        lock.lock();
        try {
            //队列满了
            while (storage.size() == maxSize) {
                System.out.println(Thread.currentThread().getName() + ":wait:" + storage.size());
                notFull.await();//阻塞生产队列
            }
            storage.add(new Date());
            System.out.println(Thread.currentThread().getName() + ":put:" + storage.size());
            Thread.sleep(1000);
            notEmpty.signal();//唤醒消费线程
        } catch (InterruptedException e) {

        } finally {
            lock.unlock();
        }
    }

    public Date take() {
        lock.lock();
        try {
            //队列为空
            while (storage.size() == 0) {
                System.out.println(Thread.currentThread().getName() + ":wait:" + storage.size());
                notEmpty.await();//阻塞消费线程
            }
            Date date = storage.poll();
            System.out.println(Thread.currentThread().getName() + ":take:" + storage.size());
            notFull.signal();
            return date;

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }

    static class Consumer implements Runnable {

        private Buffer buffer;

        Consumer(Buffer buffer) {
            this.buffer = buffer;
        }

        @Override
        public void run() {
            buffer.take();
        }
    }

    static class Producer implements Runnable {

        private Buffer buffer;

        Producer(Buffer buffer) {
            this.buffer = buffer;
        }

        @Override
        public void run() {
            buffer.put();
        }
    }

    public static void main(String[] args) {
        Buffer buffer = new Buffer(2);
        Producer producer = new Producer(buffer);
        Consumer consumer = new Consumer(buffer);
        for (int i = 0; i < 3; i++) {
            new Thread(producer, "producer-" + i).start();
        }
        for (int i = 0; i < 3; i++) {
            new Thread(consumer, "consumer-" + i).start();
        }
    }

}
