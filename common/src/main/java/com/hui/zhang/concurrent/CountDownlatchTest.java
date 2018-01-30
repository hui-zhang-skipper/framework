package com.hui.zhang.concurrent;

import java.util.concurrent.CountDownLatch;

public class CountDownlatchTest {

    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(new readNum(i, countDownLatch)).start();
        }
        countDownLatch.await();
        //阻塞在主线程 等待所有子线程都执行完毕，再执行主线程
        System.out.println("线程执行结束。。。。");
    }


    static class readNum implements Runnable {
        private int id;
        private CountDownLatch latch;

        public readNum(int id, CountDownLatch latch) {
            this.id = id;
            this.latch = latch;
        }

        @Override
        public void run() {
            synchronized (readNum.class) {
                System.out.println("id:" + id);
                latch.countDown();
                //之后继续执行自己的任务
                System.out.println("线程组任务" + id + "结束，其他任务继续");
            }
        }
    }
}


