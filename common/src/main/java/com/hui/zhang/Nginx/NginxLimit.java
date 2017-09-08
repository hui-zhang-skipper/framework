package com.hui.zhang.Nginx;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author zhanghui32
 * @date 2017/9/7
 */
public class NginxLimit {
    public static void main(String[] args) throws IOException, InterruptedException {
        final NginxLimit distrubuteLimit = new NginxLimit();
        final CountDownLatch latch = new CountDownLatch(1);//两个工人的协作
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Runnable() {
                public void run() {
                    try {
                        latch.await();
                        String rev = distrubuteLimit.sendGet("http://127.0.0.1/limit", null);
                        System.out.println(rev);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();
        }
        latch.countDown();
        System.in.read();
    }

    public String sendGet(String url, String param) {
        return HttpRequest.sendGet(url, param);
    }
}
