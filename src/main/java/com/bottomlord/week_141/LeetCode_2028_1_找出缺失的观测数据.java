package com.bottomlord.week_141;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2022-03-27 22:27:16
 */
public class LeetCode_2028_1_找出缺失的观测数据 {
    public int[] missingRolls(int[] rolls, int mean, int n) {
        int m = rolls.length;
        int sum = (n + m) * mean;

        int mSum = Arrays.stream(rolls).sum(), nSum = sum - mSum;

        if (nSum < n) {
            return new int[0];
        }

        int x = nSum / n, y = nSum % n;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = i >= y ? x + 1 : x;
        }

        return ans;
    }
}
