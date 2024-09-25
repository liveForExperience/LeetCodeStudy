package com.bottomlord.week_166;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2022-09-14 08:21:07
 */
public class LeetCode_2404_1_出现最频繁的偶数元素 {
    public int mostFrequentEven(int[] nums) {
        Arrays.sort(nums);
        int max = Integer.MIN_VALUE, ans = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 1) {
                continue;
            }

            int j = i;
            while (j < nums.length && nums[i] == nums[j]) {
                j++;
            }

            if (j - i + 1 > max) {
                ans = nums[i];
                max = j - i + 1;
            }
        }

        return ans;
    }
}
