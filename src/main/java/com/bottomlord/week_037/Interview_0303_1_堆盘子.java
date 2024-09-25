package com.bottomlord.week_037;

import java.util.LinkedList;

/**
 * @author ThinkPad
 * @date 2020/3/21 19:20
 */
public class Interview_0303_1_堆盘子 {
    class StackOfPlates {
        private LinkedList<int[]> list;
        private int cap;
        private int size;
        public StackOfPlates(int cap) {
            this.cap = cap;
            this.list = new LinkedList<>();
            this.list.add(new int[cap + 1]);
        }

        public void push(int val) {
            if (cap == 0) {
                return;
            }

            if (list.get(size)[0] == cap) {
                list.add(new int[cap + 1]);
                size++;
            }

            int[] stack = list.get(size);
            stack[0]++;
            stack[stack[0]] = val;
        }

        public int pop() {
            if (size == 0 && list.getFirst()[0] == 0) {
                return -1;
            }

            int[] stack = list.get(size);
            int num =  stack[stack[0]--];
            if (stack[0] == 0 && size != 0) {
                list.removeLast();
                size--;
            }
            return num;
        }

        public int popAt(int index) {
            if (size < index || (size == 0 && list.getFirst()[0] == 0)) {
                return -1;
            }

            int[] stack = list.get(index);
            int num = stack[stack[0]--];
            if (stack[0] == 0 && size != 0) {
                list.remove(index);
                size--;
            }
            return num;
        }
    }
}
