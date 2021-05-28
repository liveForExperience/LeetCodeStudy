package com.bottomlord.week_098;

/**
 * @author ChenYue
 * @date 2021/5/28 8:31
 */
public class LeetCode_1114_1_按序打印 {
    class Foo {
        private volatile boolean twoCanRun;
        private volatile boolean threeCanRun;
        public Foo() {

        }

        public void first(Runnable printFirst) throws InterruptedException {
            printFirst.run();
            twoCanRun = true;
        }

        public void second(Runnable printSecond) throws InterruptedException {
            while (!twoCanRun) {}
            printSecond.run();
            threeCanRun = true;
        }

        public void third(Runnable printThird) throws InterruptedException {
            while (!threeCanRun) {}
            printThird.run();
        }
    }
}
