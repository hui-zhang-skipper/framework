package com.hui.zhang.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ConcurrentHashMap;

public class Client {
    /**
     * 处理服务端发回的对象，可实现该接口。
     */
    public static interface ObjectAction {
        void doAction(Object obj, Client client);
    }

    public static final class DefaultObjectAction implements ObjectAction {
        public void doAction(Object obj, Client client) {
            System.out.println("处理：\t" + obj.toString());
        }
    }

    private String serverIp = "127.0.0.1";
    private int port = 8011;
    private Socket socket;

    private long lastSendTime; //最后一次发送数据的时间

    private boolean running = false; //连接状态

    //用于保存接收消息对象类型及该类型消息处理的对象
    private ConcurrentHashMap<Class, ObjectAction> actionMapping = new ConcurrentHashMap<Class, ObjectAction>();

    private static Object object=new Object();


    public Client(String serverIp, int port) {
        this.serverIp = serverIp;
        this.port = port;
    }

    public void start() throws UnknownHostException, Exception {
        socket = new Socket(serverIp, port);
        System.out.println("本地端口：" + socket.getLocalPort());
        lastSendTime = System.currentTimeMillis();
        running = true;
        new Thread(new KeepAliveWatchDog()).start();  //保持长连接的线程，每隔2秒项服务器发一个一个保持连接的心跳消息
        new Thread(new ReceiveWatchDog()).start();    //接受消息的线程，处理消息


    }

    /**
     * tcp 监听close_wait 状态
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        synchronized (object) {
            final int length = 10;
            String serverIp = "localhost";
            int port = 8011;
//        for (int i = 0; i < 10; i++) {
//            Client client = new Client(serverIp, port);
//            client.start();
//        }
            Socket[] sockets = new Socket[length];
            for (int i = 0; i < length; i++) {
                try {
                    sockets[i] = new Socket(serverIp, port);
                    System.out.println(sockets[i].getLocalPort());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            object.wait();
        }
        //Thread.yield();
        //Thread.sleep(50000);
    }


    public void stop() {
        if (running) running = false;
    }

    /**
     * 添加接收对象的处理对象。
     *
     * @param cls    待处理的对象，其所属的类。
     * @param action 处理过程对象。
     */
    public void addActionMap(Class<Object> cls, ObjectAction action) {
        actionMapping.put(cls, action);
    }

    public void sendObject(Object obj) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(obj);
        System.out.println("发送：\t" + obj);
        oos.flush();
    }

    class KeepAliveWatchDog implements Runnable {
        long checkDelay = 10;
        long keepAliveDelay = 2000;

        public void run() {
            while (running) {
                if (System.currentTimeMillis() - lastSendTime > keepAliveDelay) {
                    try {
                        Client.this.sendObject(new KeepAlive());
                    } catch (IOException e) {
                        e.printStackTrace();
                        Client.this.stop();
                    }
                    lastSendTime = System.currentTimeMillis();
                } else {
                    try {
                        Thread.sleep(checkDelay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Client.this.stop();
                    }
                }
            }

        }
    }

    class ReceiveWatchDog implements Runnable {
        public void run() {
            while (running) {
                try {
                    InputStream in = socket.getInputStream();
                    if (in.available() > 0) {
                        ObjectInputStream ois = new ObjectInputStream(in);
                        Object obj = ois.readObject();
                        System.out.println("接收：\t" + obj);
                        ObjectAction oa = actionMapping.get(obj.getClass());
                        oa = oa == null ? new DefaultObjectAction() : oa;
                        oa.doAction(obj, Client.this);
                    } else {
                        Thread.sleep(10);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Client.this.stop();
                }
            }
        }
    }
}
