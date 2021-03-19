package com.bottomlord.week_088;

/**
 * @author ChenYue
 * @date 2021/3/19 8:25
 */
public class LeetCode_494_1_目标和 {
    private int ans, target;
    public int findTargetSumWays(int[] nums, int S) {
        target = S;
        backTrack(nums, 0, 0);
        return ans;
    }

    private boolean backTrack(int[] nums, int index, int sum) {
        if (index == nums.length) {
            if (sum == target) {
                ans++;
                return true;
            }
            return false;
        }

        return backTrack(nums, index + 1, sum + nums[index]) |
               backTrack(nums, index + 1, sum - nums[index]);
    }
}
