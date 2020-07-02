package com.bottomlord.week_052;

import java.util.Stack;

/**
 * @author ChenYue
 * @date 2020/7/1 8:44
 */
public class LeetCode_81_2 {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int row = matrix.length, col = matrix[0].length, ans = 0;
        int[] dp = new int[col];

        for (char[] chars : matrix) {
            for (int c = 0; c < col; c++) {
                dp[c] = chars[c] == '1' ? dp[c] + 1 : 0;
            }

            ans = Math.max(ans, getMaxRectangle(dp));
        }

        return ans;
    }

    private int getMaxRectangle(int[] dp) {
        int max = 0, len = dp.length;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i <= len; i++) {
            int cur = i == len ? -1 : dp[i];
            while (!stack.isEmpty() && dp[stack.peek()] > cur) {
                int index = stack.pop();
                int width = dp[index];

                if (stack.isEmpty()) {
                    max = Math.max(width * i, max);
                } else {
                    max = Math.max(width * (i - stack.peek() - 1), max);
                }
            }

            stack.push(i);
        }
        return max;
    }
}
