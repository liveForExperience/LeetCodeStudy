package com.bottomlord.week_051;

/**
 * @author ChenYue
 * @date 2020/6/28 12:43
 */
public class LeetCode_209_2 {
    public int minSubArrayLen(int s, int[] nums) {
        int ans = Integer.MAX_VALUE, sum = 0, start = 0, end = 0;
        for (; end < nums.length; end++) {
            sum += nums[end];

            while (sum >= s) {
                ans = Math.min(ans, end - start + 1);
                sum -= nums[start];
                start++;
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}