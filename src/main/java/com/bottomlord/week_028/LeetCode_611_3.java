package com.bottomlord.week_028;

import java.util.Arrays;

/**
 * @author ThinkPad
 * @date 2020/1/19 18:17
 */
public class LeetCode_611_3 {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int k = i + 2;

            for (int j = i + 1; j < nums.length - 1 && nums[i] != 0; j++) {
                while (k < nums.length && nums[i] + nums[j] > nums[k]) {
                    k++;
                    ans += k - j - 1;
                }
            }
        }

        return ans;
    }
}
