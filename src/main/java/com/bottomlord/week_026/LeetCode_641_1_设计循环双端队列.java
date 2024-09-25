package com.bottomlord.week_026;

/**
 * @author ThinkPad
 * @date 2020/1/3 9:00
 */
public class LeetCode_641_1_设计循环双端队列 {
    class MyCircularDeque {
        private int cap;
        private int front;
        private int rear;
        private int[] queue;

        public MyCircularDeque(int k) {
            cap = k + 1;
            front = rear = 0;
            queue = new int[cap];
        }

        public boolean insertFront(int value) {
            if (isFull()) {
                return false;
            }

            queue[front = moveBackward(front)] = value;
            return true;
        }

        public boolean insertLast(int value) {
            if (isFull()) {
                return false;
            }

            queue[rear] = value;
            rear = moveForward(rear);
            return true;
        }

        public boolean deleteFront() {
            if (isEmpty()) {
                return false;
            }

            front = moveForward(front);
            return true;
        }

        public boolean deleteLast() {
            if (isEmpty()) {
                return false;
            }

            rear = moveBackward(rear);
            return true;
        }

        public int getFront() {
            return isEmpty() ? -1 : queue[front];
        }

        public int getRear() {
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
