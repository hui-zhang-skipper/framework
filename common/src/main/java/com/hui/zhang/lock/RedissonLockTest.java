package com.hui.zhang.lock;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RedissonLockTest {

    static int fixNum = 5;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(fixNum);

        ExecutorService exec = Executors.newFixedThreadPool(fixNum);

        RedissonClient redisson = Redisson.create();

        for (int i = 0; i < fixNum; i++) {
            exec.submit(new TestLock("client" + i, redisson, latch));
        }
        exec.shutdown();
        latch.await();
        System.out.printf("所有任务执行完成");
    }

    static class TestLock implements Runnable {

        private String name;
        private RedissonClient redisson;

        private CountDownLatch latch;

        public TestLock(String name, RedissonClient redisson, CountDownLatch latch) {
            this.name = name;
            this.latch = latch;
            this.redisson = redisson;
        }

        @Override
        public void run() {
            RLock lock = redisson.getLock("TestLock");
            try {
                if (lock.tryLock(300, 30, TimeUnit.MILLISECONDS)) {
                    try {

                        System.out.printf("获取锁成功");
                        Thread.sleep(2 * 1000);
                        latch.countDown();
                    } finally {
                        lock.unlock();
                    }
                }
            } catch (Exception e) {

            }
        }
    }
}
