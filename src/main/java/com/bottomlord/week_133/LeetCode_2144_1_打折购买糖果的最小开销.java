package com.bottomlord.week_133;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2022-01-27 09:26:56
 */
public class LeetCode_2144_1_打折购买糖果的最小开销 {
    public int minimumCost(int[] cost) {
        int n = cost.length, left = n % 3 == 0 ? 0 : 3 - (n % 3), sum = 0;
        Arrays.sort(cost);
        int[] arr = new int[n + left];
        System.arraycopy(cost, 0, arr, left, n);

        for (int i = arr.length - 1; i >= 0;) {
            sum += arr[i--];
            sum += arr[i--];
            i--;
        }

        return sum;
    }
}
