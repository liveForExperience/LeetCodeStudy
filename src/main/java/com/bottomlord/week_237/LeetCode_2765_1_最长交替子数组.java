package com.bottomlord.week_237;

import com.bottomlord.LeetCodeUtils;

/**
 * @author chen yue
 * @date 2024-01-23 09:05:30
 */
public class LeetCode_2765_1_最长交替子数组 {
    public int alternatingSubarray(int[] nums) {
        int cnt = -1, n = nums.length;
        for (int i = 1; i < n;) {
            int c = 1;
            boolean flag = true;
            while (i < n && nums[i] - nums[i - 1] == (flag ? 1 : -1)) {
                flag = !flag;
                i++;
                c++;
            }

            if (c > 1) {
                cnt = Math.max(cnt, c);
            }

            if (c == 1) {
                i++;
            }
        }

        return cnt;
    }
}
