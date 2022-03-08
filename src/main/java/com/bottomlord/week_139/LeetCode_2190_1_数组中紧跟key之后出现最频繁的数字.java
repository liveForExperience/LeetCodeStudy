package com.bottomlord.week_139;

/**
 * @author chen yue
 * @date 2022-03-08 14:19:31
 */
public class LeetCode_2190_1_数组中紧跟key之后出现最频繁的数字 {
    public int mostFrequent(int[] nums, int key) {
        int max = 0, ans = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != key) {
                continue;
            }

            if (i + 1 == nums.length) {
                continue;
            }

            int target = nums[i + 1], count = 1;
            for (int j = i + 2; j < nums.length; j++) {
                if (nums[j] == target && nums[j - 1] == key) {
                    count++;
                }
            }

            if (count > max) {
                max = count;
                ans = target;
            }
        }

        return ans;
    }
}
