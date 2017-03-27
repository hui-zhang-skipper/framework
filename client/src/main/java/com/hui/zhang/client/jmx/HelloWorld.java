package com.hui.zhang.client.jmx;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhanghui32
 * @date 2017/3/22
 */
public class HelloWorld {
    public String sayHello(String name) {
        return "hello " + name;
    }

    public void getHello(String name) {
        System.out.println("hello world " + name);
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("SpringApplication.xml");
        while (true) {
        }
    }
}
