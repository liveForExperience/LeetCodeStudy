package com.bottomlord.week_032;

import java.util.Stack;

/**
 * @author ThinkPad
 * @date 2020/2/15 17:04
 */
public class Interview_09_1_用两个栈实现队列 {
    class CQueue {
        private Stack<Integer> in;
        private Stack<Integer> out;
        public CQueue() {
            this.in = new Stack<>();
            this.out = new Stack<>();
        }

        public void appendTail(int value) {
            in.push(value);
        }

        public int deleteHead() {
            if (in.isEmpty()) {
                return -1;
            }

            while (!in.isEmpty()) {
                out.push(in.pop());
            }

            int ans = out.pop();

            while (!out.isEmpty()) {
                in.push(out.pop());
            }

            return ans;
        }
    }
}
