package com.learning.thread.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class A1B2C3_ReentrantLockTest {

    public static void main(String[] args) {
        char[] c1 = "ABCDEFG".toCharArray();
        char[] c2 = "1234567".toCharArray();

        ReentrantLock lock = new ReentrantLock();
        Condition conditionT1 = lock.newCondition();
        Condition conditionT2 = lock.newCondition();

        new Thread(() -> {
            try {
                lock.lock();

                for (char c : c1) {
                    System.out.print(c);
                    conditionT2.signal();
                    conditionT1.await();

                }
                conditionT2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }, "t1").start();

        new Thread(() -> {
            try {
                lock.lock();

                for (char c : c2) {
                    System.out.print(c);
                    conditionT1.signal();
                    conditionT2.await();
                }
                conditionT1.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }
}
