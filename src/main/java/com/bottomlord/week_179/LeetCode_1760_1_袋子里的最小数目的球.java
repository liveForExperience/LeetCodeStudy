package com.bottomlord.week_179;

import java.util.Arrays;
import java.util.Map;

/**
 * @author chen yue
 * @date 2022-12-20 22:47:10
 */
public class LeetCode_1760_1_袋子里的最小数目的球 {
    public int minimumSize(int[] nums, int maxOperations) {
        Arrays.sort(nums);
        int ans = Integer.MAX_VALUE, left = 1, right = nums[nums.length - 1];

        while (left < right) {
            int mid = left + (right - left) / 2;
            int opt = 0;
            for (int num : nums) {
                opt += (num - 1) / mid;
            }

            if (opt <= maxOperations) {
                ans = Math.min(ans, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }
}
