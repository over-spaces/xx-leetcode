package com.learning.juc.test;

public class T_001_A1B2C3_WaitNotifyTest {

    public static void main(String[] args) {

        final String STR = "ABCDEFG";
        final String NUM = "1234567";

        final Object lock = new Object();

        Thread t1 = new Thread(() -> {

            synchronized (lock){
                for (char s : STR.toCharArray()){
                    System.out.print(s);
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }

        }, "t1");

        Thread t2 = new Thread(() -> {

            synchronized (lock){
                for (char n : NUM.toCharArray()){
                    System.out.print(n);
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }

        }, "t1");

        t1.start();
        t2.start();

    }

}
