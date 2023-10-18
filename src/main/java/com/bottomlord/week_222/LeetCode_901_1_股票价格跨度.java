package com.bottomlord.week_222;

import java.util.LinkedList;

/**
 * @author chen yue
 * @date 2023-10-07 09:50:05
 */
public class LeetCode_901_1_股票价格跨度 {
    class StockSpanner {

        private int index;
        private LinkedList<int[]> stack;

        public StockSpanner() {
            this.index = 0;
            this.stack = new LinkedList<>();
        }

        public int next(int price) {
            while (!stack.isEmpty() && stack.peek()[0] <= price) {
                stack.poll();
            }

            if (stack.isEmpty()) {
                stack.push(new int[]{price, index++});
                return index;
            }

            int[] peek = stack.peek();
            stack.push(new int[]{price, index});
            return index++ - peek[1];
        }
    }
}
