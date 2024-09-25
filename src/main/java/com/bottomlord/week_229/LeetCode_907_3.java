package com.bottomlord.week_229;

import java.util.ArrayDeque;

/**
 * @author chen yue
 * @date 2023-11-27 19:28:22
 */
public class LeetCode_907_3 {
    public int sumSubarrayMins(int[] arr) {
        int n = arr.length, mod = 1000000007;
        int[] lefts = new int[n], rights = new int[n];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int num = arr[i];
            while (!stack.isEmpty() && arr[stack.peek()] >= num) {
                stack.pop();
            }

            lefts[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            stack.push(i);
        }

        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            int num = arr[i];
            while (!stack.isEmpty() && arr[stack.peek()] > num) {
                stack.pop();
            }

            rights[i] = stack.isEmpty() ? n - i : stack.peek() - i;
            stack.push(i);
        }

        long ans = 0L;
        for (int i = 0; i < n; i++) {
            ans = (ans + (long)lefts[i] * rights[i] * arr[i]) % mod;
        }

        return (int)ans;
    }
}
