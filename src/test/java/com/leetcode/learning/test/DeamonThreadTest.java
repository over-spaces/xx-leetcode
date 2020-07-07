package com.leetcode.learning.test;

/**
 * 二者其实基本上是一样的。唯一的区别在于JVM何时离开。
 *
 * 用户线程：当存在任何一个用户线程未离开，JVM是不会离开的。
 *
 * 守护线程：如果只剩下守护线程未离开，JVM是可以离开的。
 */
public class DeamonThreadTest {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    System.out.println("内部线程：正在执行。。。。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        //开启守护进程
        thread.setDaemon(true);
        thread.start();

        Thread.sleep(5000);

        System.out.println("Main thread finished lifecycle !!!!!!!!!!");
    }
}
