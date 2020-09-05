package com.learning.thread.test;

public class A1B2C3_NotifyWaitTest {

    public static void main(String[] args) {

        Object obj = new Object();

        char[] c1 = "ABCDEFG".toCharArray();
        char[] c2 = "1234567".toCharArray();

        Thread t1 = new Thread(() -> {
            synchronized (obj){
                for (char c : c1) {
                    System.out.print(c);
                    try {
                        obj.notify();
                        obj.wait();//obj所在的锁线程等待，让出锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                obj.notify();
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (obj){
                for (char c : c2) {
                    System.out.print(c);
                    try {
                        obj.notify();
                        obj.wait();//obj所在的锁线程等待，让出锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                obj.notify();
            }
        });

        t1.start();
        t2.start();
    }
}
