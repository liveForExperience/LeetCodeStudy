package com.bottomlord.week_160;

/**
 * @author chen yue
 * @date 2022-08-02 08:47:14
 */
public class LeetCode_622_1_设计循环队列 {
    class MyCircularQueue {
        private int front, rear, cap;
        private int[] queue;
        public MyCircularQueue(int k) {
            cap = k + 1;
            this.queue = new int[cap];
        }

        public boolean enQueue(int value) {
            if (isFull()) {
                return false;
            }

            queue[rear] = value;
            rear = moveForward(rear);
            return true;
        }

        public boolean deQueue() {
            if (isEmpty()) {
                return false;
            }

            front = moveForward(front);
            return true;
        }

        public int Front() {
            return isEmpty() ? -1 : queue[front];
        }

        public int Rear() {
            return isEmpty() ? -1 : queue[moveBack(rear)];
        }

        public boolean isEmpty() {
            return front == rear;
        }

        public boolean isFull() {
            return front == moveForward(rear);
        }

        private int moveForward(int pos) {
            return (pos + 1) % cap;
        }

        private int moveBack(int pos) {
            return (pos + cap - 1) % cap;
        }
    }
}
