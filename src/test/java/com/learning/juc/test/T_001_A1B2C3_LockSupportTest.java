package com.learning.juc.test;

import java.util.concurrent.locks.LockSupport;

public class T_001_A1B2C3_LockSupportTest {

    static Thread t1 = null, t2 = null;


    public static void main(String[] args) {

        final String STR = "ABCDEFG";
        final String NUM = "1234567";


        t1 = new Thread(() -> {

            for (char s : STR.toCharArray()) {
                System.out.print(s);
                LockSupport.unpark(t2);
                LockSupport.park();
            }

        }, "t1");

        t2 = new Thread(() -> {

            for (char n : NUM.toCharArray()) {
                LockSupport.park();
                System.out.print(n);
                LockSupport.unpark(t1);

            }

        }, "t1");

        t1.start();
        t2.start();

    }

}
