package com.bottomlord.week_130;

/**
 * @author chen yue
 * @date 2022-01-05 09:00:55
 */
public class LeetCode_1991_1_找到数组的中间位置 {
    public int findMiddleIndex(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }

        int[] sums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }

        for (int i = 0; i < n; i++) {
            if (i == 0) {
                if (sums[n] - sums[1] == 0) {
                    return 0;
                }
                continue;
            }

            if (i == n - 1) {
                if (sums[n - 1] - sums[0] == 0) {
                    return n - 1;
                }
                continue;
            }

            if (sums[i] - sums[0] == sums[n] - sums[i + 1]) {
                return i;
            }
        }

        return -1;
    }
}
