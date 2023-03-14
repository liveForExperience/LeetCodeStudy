package com.bottomlord.week_192;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author chen yue
 * @date 2023-03-13 08:52:12
 */
public class LeetCode_2281_1_巫师的总力量和 {
    public int totalStrength(int[] strength) {
        int n = strength.length, mod = (int) 1e9 + 7;
        int[] lefts = new int[n], rights = new int[n];
        Arrays.fill(lefts, -1);
        Arrays.fill(rights, n);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && strength[stack.peek()] >= strength[i]) {
                rights[stack.pop()] = i;
            }

            if (!stack.isEmpty()) {
                lefts[i] = stack.peek();
            }

            stack.push(i);
        }

        long sum = 0L;
        int[] ss = new int[n + 2];
        for (int i = 1; i <= n; i++) {
            sum = sum + strength[i - 1];
            ss[i + 1] = (int) ((ss[i] + sum) % mod);
        }

        long ans = 0L;
        for (int i = 0; i < n; i++) {
            int l = lefts[i] + 1, r = rights[i] - 1;
            long tot = ((long) (i - l + 1) * (ss[r + 2] - ss[i + 1]) - (long) (r - i + 1) * (ss[i + 1] - ss[l])) % mod;
            ans = (ans + strength[i] * tot) % mod;

        }
        return (int) (ans + mod) % mod;
    }
}
