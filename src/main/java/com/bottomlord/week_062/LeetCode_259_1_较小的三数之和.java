package com.bottomlord.week_062;

/**
 * @author ChenYue
 * @date 2020/9/8 8:18
 */
public class LeetCode_259_1_较小的三数之和 {
    public int threeSumSmaller(int[] nums, int target) {
        int len = nums.length, ans = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    if (nums[i] + nums[j] + nums[k] < target) {
                        ans++;
                    }
                }
            }
        }

        return ans;
    }
}
