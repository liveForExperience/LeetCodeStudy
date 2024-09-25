package com.bottomlord.week_171;

import java.util.Stack;

/**
 * @author chen yue
 * @date 2022-10-21 17:36:10
 */
public class LeetCode_901_1_股票价格跨度 {
    class StockSpanner {

        private int index;
        private Stack<int[]> stack;

        public StockSpanner() {
            this.index = 0;
            this.stack = new Stack<>();
        }

        public int next(int price) {
            while (!stack.isEmpty() && stack.peek()[0] <= price) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                stack.push(new int[]{price, index++});
                return index - 1;
            }

            int[] arr = stack.peek();
            stack.push(new int[]{price, index});

            return index++ - arr[1] - 1;
        }
    }
}
