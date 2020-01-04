package com.bottomlord.week_026;

/**
 * @author ThinkPad
 * @date 2020/1/4 9:40
 */
public class LeetCode_622_1_设计循环队列 {
    class MyCircularQueue {
        private int cap;
        private int front;
        private int rear;
        private int[] queue;

        public MyCircularQueue(int k) {
            cap = k + 1;
            front = rear = 0;
            queue = new int[cap];
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
            return isEmpty() ? -1 : queue[moveBackward(rear)];
        }

        public boolean isEmpty() {
            return front == rear;
        }

        public boolean isFull() {
            return (rear + 1) % cap == front;
        }

        private int moveForward(int pos) {
            return (pos + 1) % cap;
        }

        private int moveBackward(int pos) {
            return (pos - 1 + cap) % cap;
        }
    }
}
