package com.bottomlord.week_162;

/**
 * @author chen yue
 * @date 2022-08-15 08:47:48
 */
public class LeetCode_641_1_设计循环双端队列 {
    class MyCircularDeque {
        private final int cap;
        private int front, rear;
        private final int[] queue;

        public MyCircularDeque(int k) {
            this.cap = k + 1;
            this.queue = new int[cap];
            this.front = rear = 0;
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
            return moveForward(rear) == front;
        }

        private int moveForward(int index) {
            return (index + 1) % cap;
        }

        private int moveBackward(int index) {
            return (index - 1 + cap) % cap;
        }
    }
}
