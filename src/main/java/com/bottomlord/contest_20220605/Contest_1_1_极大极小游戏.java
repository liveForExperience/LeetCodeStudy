package com.bottomlord.contest_20220605;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2022-06-05 10:28:39
 */
public class Contest_1_1_极大极小游戏 {
    public int partitionArray(int[] nums, int k) {
        int n = nums.length, ans = 0;
        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            int cur = nums[i];

            while (i + 1 < n && nums[i + 1] <= cur + k) {
                i++;
            }

            ans++;
        }

        return ans;
    }
}
