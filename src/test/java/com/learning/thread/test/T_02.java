package com.learning.thread.test;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class T_02 {

    private static LinkedList<String> c = new LinkedList<>();
    private static final int MAX = 10;
    private int count = 0;

    public synchronized String get() throws InterruptedException {
        while (c.size() == 0){
            this.wait();
        }
        count--;
        String v = c.removeFirst();
        this.notifyAll();
        return v;
    }

    public synchronized void put(String value) throws InterruptedException {
        while (c.size() == MAX){
            this.wait();
        }
        ++count;
        c.add(value);
        this.notifyAll();
    }

    public static void main(String[] args) {
        T_02 t = new T_02();
        //启动消费者线程
        for(int i=0; i<10; i++) {
            new Thread(()->{
                for(int j=0; j<5; j++) {
                    try {
                        System.out.println("消费：" + t.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "c" + i).start();
        }


        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //启动生产者线程
        for(int i=0; i<2; i++) {
            new Thread(()->{
                for(int j=0; j<25; j++) {
                    try {
                        t.put(Thread.currentThread().getName() + " " + j);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "p" + i).start();
        }

    }
}
