package com.bottomlord.week_172;

import java.util.Stack;

/**
 * @author chen yue
 * @date 2022-10-29 09:09:47
 */
public class LeetCode_907_1_子数组的最小值之和 {
    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        int[] lefts = new int[n], rights = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                lefts[i] = -1;
            } else {
                lefts[i] = stack.peek();
            }

            stack.push(i);
        }

        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                rights[i] = n;
            } else {
                rights[i] = stack.peek();
            }

            stack.push(i);
        }

        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans = (ans + (long)arr[i] * (i - lefts[i]) * (rights[i] - i)) % 1000000007;
        }

        return (int)ans;
    }
}
