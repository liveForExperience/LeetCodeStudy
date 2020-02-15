package com.bottomlord.week_032;

import java.util.Stack;

/**
 * @author ThinkPad
 * @date 2020/2/15 19:02
 */
public class Interview_09_2 {
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
            if (out.isEmpty()) {
                while (!in.isEmpty()) {
                    out.push(in.pop());
                }
            }

            return out.isEmpty() ? -1 : out.pop();
        }
    }
}
