package com.bottomlord.week_046;

/**
 * @author ChenYue
 * @date 2020/5/18 8:41
 */
public class LeetCode_152_2 {
    public int maxProduct(int[] nums) {
        int max = nums[0], min = nums[0], ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int curMax = max, curMin = min, num = nums[i];
            max = Math.max(curMax * num, Math.max(curMin * num, num));
            min = Math.min(curMax * num, Math.min(curMin * num, num));
            ans = Math.max(max, ans);
        }

        return ans;
    }
}
