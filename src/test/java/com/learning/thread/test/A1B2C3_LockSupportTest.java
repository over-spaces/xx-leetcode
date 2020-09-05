package com.learning.thread.test;

import java.util.concurrent.locks.LockSupport;

public class A1B2C3_LockSupportTest {

    static Thread t1 = null;
    static Thread t2 = null;
    public static void main(String[] args) {

        char[] c1 = "ABCDEF".toCharArray();
        char[] c2 = "123456".toCharArray();


        t1 = new Thread(() -> {
            for (char c : c1) {
                System.out.print(c);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        });

        t2 = new Thread(() -> {
            for (char c : c2) {
                LockSupport.park();
                System.out.print(c);
                LockSupport.unpark(t1);
            }
        });
        t1.start();
        t2.start();
    }

}
