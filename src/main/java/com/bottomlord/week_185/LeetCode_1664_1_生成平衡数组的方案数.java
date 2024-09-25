package com.bottomlord.week_185;

/**
 * @author chen yue
 * @date 2023-01-28 09:27:33
 */
public class LeetCode_1664_1_生成平衡数组的方案数 {
    public int waysToMakeFair(int[] nums) {
        int n = nums.length;
        int[] odds = new int[n + 1], evens = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (i % 2 == 0) {
                evens[i] = evens[i - 1] + nums[i - 1];
                odds[i] = odds[i - 1];
            } else {
                evens[i] = evens[i - 1];
                odds[i] = odds[i - 1] + nums[i - 1];
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            int leftOdd = odds[i], rightOdd = odds[n] - odds[i + 1],
                leftEven = evens[i], rightEven = evens[n] - evens[i + 1];

            if (leftOdd + rightEven == leftEven + rightOdd) {
                count++;
            }
        }

        return count;
    }
}
