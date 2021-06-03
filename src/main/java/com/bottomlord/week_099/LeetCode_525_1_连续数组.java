package com.bottomlord.week_099;

/**
 * @author ChenYue
 * @date 2021/6/3 8:13
 */
public class LeetCode_525_1_连续数组 {
    public int findMaxLength(int[] nums) {
        int[] sums = new int[nums.length + 1];
        for (int i = 1; i < sums.length; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }

        int ans = 0;
        for (int i = 0; i < sums.length; i++) {
            for (int j = i + 2; j < sums.length; j += 2) {
                if ((j - i) / 2 == sums[j] - sums[i]) {
                    ans = Math.max(ans, j - i);
                }
            }
        }

        return ans;
    }
}
