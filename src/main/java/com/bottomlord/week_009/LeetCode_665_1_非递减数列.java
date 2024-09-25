package com.bottomlord.week_009;

public class LeetCode_665_1_非递减数列 {
    public boolean checkPossibility(int[] nums) {
        int count = 0;

        for (int i = 0; i + 1 < nums.length; i++) {
            if (nums[i] > nums[i + 1]) {
                if (count > 1) {
                    return false;
                }

                if (i == 0 || i == nums.length - 2 || nums[i - 1] <= nums[i + 1] || nums[i] <= nums[i + 2]) {
                    count++;
                    continue;
                }

                return false;
            }
        }

        return true;
    }
}
