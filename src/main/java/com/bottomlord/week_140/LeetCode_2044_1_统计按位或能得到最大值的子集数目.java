package com.bottomlord.week_140;

/**
 * @author chen yue
 * @date 2022-03-15 20:47:35
 */
public class LeetCode_2044_1_统计按位或能得到最大值的子集数目 {
    int max = Integer.MIN_VALUE, ans = 0;
    public int countMaxOrSubsets(int[] nums) {
        backTrack(nums, 0, 0);
        return ans;
    }

    private void backTrack(int[] nums, int start, int num) {
        if (start >= nums.length) {
            return;
        }

        for (int i = start; i < nums.length; i++) {
            int cur = num | nums[i];
            if (cur > max) {
                max = cur;
                ans = 1;
            } else if (cur == max) {
                ans++;
            }

            backTrack(nums, i + 1, cur);
        }
    }
}
