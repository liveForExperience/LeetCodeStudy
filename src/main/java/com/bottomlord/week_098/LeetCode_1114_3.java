package com.bottomlord.week_098;

import java.util.concurrent.Semaphore;

/**
 * @author ChenYue
 * @date 2021/5/28 8:50
 */
public class LeetCode_1114_3 {
    class Foo {
        private volatile int n = 0;
        private final Object lock = new Object();
        public Foo() {}

        public void first(Runnable printFirst) throws InterruptedException {
            synchronized (lock) {
                printFirst.run();
                n++;
                lock.notifyAll();
            }
        }

        public void second(Runnable printSecond) throws InterruptedException {
            synchronized (lock) {
                while (n != 1) {
                    lock.wait();
                }
                printSecond.run();
                n++;
                lock.notifyAll();
            }
        }

        public void third(Runnable printThird) throws InterruptedException {
            synchronized (lock) {
                while (n != 2) {
                    lock.wait();
                }
            }
            printThird.run();
        }
    }
}
