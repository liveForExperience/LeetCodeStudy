package com.bottomlord.week_098;

import java.util.concurrent.Semaphore;

/**
 * @author ChenYue
 * @date 2021/5/28 8:38
 */
public class LeetCode_1114_2 {

    class Foo {
        private Semaphore s1 = new Semaphore(0), s2 = new Semaphore(0);
        public Foo() {

        }

        public void first(Runnable printFirst) throws InterruptedException {
            printFirst.run();
            s1.release();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            s1.acquire();
            printSecond.run();
            s2.release();
        }

        public void third(Runnable printThird) throws InterruptedException {
            s2.acquire();
            printThird.run();
        }
    }
}
