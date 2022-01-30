package com.bottomlord.week_133;

/**
 * @author chen yue
 * @date 2022-01-30 14:10:15
 */
public class LeetCode_2148_2 {
    public int countElements(int[] nums) {
        int len = nums.length;
        if (len < 3) {
            return 0;
        }

        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int num : nums) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }

        int ans = 0;
        for (int num : nums) {
            if (num != max && num != min) {
                ans++;
            }
        }

        return ans;
    }
}
