package com.bottomlord.week_108;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2021/8/4 8:43
 */
public class LeetCode_611_2 {
    public int triangleNumber(int[] nums) {
        int n = nums.length, ans = 0;
        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            for (int j = i - 1, k = 0; j > 0; j--) {
                while (k < j && nums[k] + nums[j] <= nums[i]) {
                    k++;
                }

                ans += j - k;
            }
        }

        return ans;
    }
}
