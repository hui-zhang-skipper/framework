package com.hui.zhang.threads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MultipleThreadRotationUsingExplicitLockCondition {

    public static void main(String[] args) {
        PrintABCUsingCondition printABC = new PrintABCUsingCondition();
        new Thread(()->printABC.printA()).start();
        new Thread(()->printABC.printB()).start();
        new Thread(()->printABC.printC()).start();
    }
}


class PrintABCUsingCondition {

    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    private int state = 0;

    public void printA() {
        print("A", 0, conditionA, conditionB);
    }

    public void printB() {
        print("B", 1, conditionB, conditionC);
    }

    public void printC() {
        print("C", 2, conditionC, conditionA);
    }

    private void print(String name, int currentState, Condition currentCondition, Condition nextCondition) {
        for (int i = 0; i < 10; i++) {
            lock.lock();
            try {
                while (state % 3 != currentState) {
                    currentCondition.await();
                }
                System.out.printf(Thread.currentThread().getName() + "print" + name);
                state++;
                nextCondition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
