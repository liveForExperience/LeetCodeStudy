package com.bottomlord.week_037;

/**
 * @author ThinkPad
 * @date 2020/3/20 11:20
 */
public class Interview_0301_1_三合一 {
    class TripleInOne {
        private int[] stack;
        private int[] indexs;
        private int stackSize;

        public TripleInOne(int stackSize) {
            this.stackSize = stackSize;
            stack = new int[3 * stackSize];
            indexs = new int[3];

            for (int i = 0; i < indexs.length; i++) {
                indexs[i] = i * stackSize - 1;
            }
        }

        public void push(int stackNum, int value) {
            if (indexs[stackNum] >= (stackNum + 1) * stackSize - 1) {
                return;
            }

            stack[++indexs[stackNum]] = value;
        }

        public int pop(int stackNum) {
            if (isEmpty(stackNum)) {
                return -1;
            }

            return stack[indexs[stackNum]--];
        }

        public int peek(int stackNum) {
            if (isEmpty(stackNum)) {
                return -1;
            }

            return stack[indexs[stackNum]];
        }

        public boolean isEmpty(int stackNum) {
            return indexs[stackNum] < stackNum * stackSize;
        }
    }
}
