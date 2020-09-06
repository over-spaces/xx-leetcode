package com.learning.juc.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class T_001_A1B2C3_ReentrantLockTest {



    public static void main(String[] args) {

        final String STR = "ABCDEFG";
        final String NUM = "1234567";

        ReentrantLock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();


        Thread t1 = new Thread(() -> {

            try {
                lock.lock();
                for (char s : STR.toCharArray()) {
                    System.out.print(s);
                    condition2.signal();
                    condition1.await();
                }
                condition2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }, "t1");

        Thread t2 = new Thread(() -> {
            try {
                lock.lock();
                for (char n : NUM.toCharArray()) {
                    System.out.print(n);
                    condition1.signal();
                    condition2.await();
                }
                condition1.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1");

        t1.start();
        t2.start();

    }

}
