package com.learning.thread.test;

import java.util.ArrayList;
import java.util.List;

public class T_01 {

     List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {

        T_01 t = new T_01();

        final Object lock = new Object();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized(lock) {
                    System.out.println("t2启动");
                    if(t.size() != 5) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("t2 结束");
                    //通知t1继续执行
                    lock.notify();
                }
            }
        }, "t2");

        t2.start();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized(lock) {
                    System.out.println("t1启动");

                    for (int i = 0; i < 10; i++) {
                        t.add(i);
                        System.out.println("t1 add " + i);

                        if(t.size() == 5) {
                            lock.notify();
                            //释放锁，让t2得以执行
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }, "t1");

        t1.start();


    }

}
