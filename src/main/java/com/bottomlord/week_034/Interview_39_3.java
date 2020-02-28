package com.bottomlord.week_034;

/**
 * @author ThinkPad
 * @date 2020/2/28 12:32
 */
public class Interview_39_3 {
    public int majorityElement(int[] nums) {
        int ans = nums[0], time = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == ans) {
                time++;
            } else if (time > 0) {
                time--;
            } else {
                ans = nums[i];
                time++;
            }
        }

        return ans;
    }
}