package com.bottomlord.week_055;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2020/7/20 8:33
 */
public class LeetCode_312_1_戳气球 {
    public int maxCoins(int[] nums) {
        int len = nums.length;
        int[] val = new int[len + 2];
        for (int i = 1; i <= len; i++) {
            val[i] = nums[i -  1];
        }
        val[0] = val[len + 1] = 1;

        int[][] memo = new int[len + 2][len + 2];
        for (int[] arr : memo) {
            Arrays.fill(arr, -1);
        }

        return recurse(val, 0, len + 1, memo);
    }

    private int recurse(int[] val, int head, int tail, int[][] memo) {
        if (head + 1 >= tail) {
            return 0;
        }

        if (memo[head][tail] != -1) {
            return memo[head][tail];
        }

        for (int mid = head + 1; mid < tail; mid++) {
            int sum = val[mid] * val[head] * val[tail];
            sum += recurse(val, head, mid, memo) + recurse(val, mid, tail, memo);
            memo[head][tail] = Math.max(sum, memo[head][tail]);
        }

        return memo[head][tail];
    }
}