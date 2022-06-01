package com.bottomlord.contest_20220529;

import java.util.Stack;

/**
 * @author chen yue
 * @date 2022-05-29 10:24:20
 */
public class Contest_3_1_ {
    public int totalSteps(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }

    private int dfs(int[] nums, int start, int end) {
        int ans = 0;
        int l = start;
        for (int i = start + 1; i <= end; i++) {
            if (nums[i] >= nums[l]) {
                continue;
            }

            ans = Math.max(ans, dfs(nums, l, i - 1));
        }

        return ans;
    }

    public static void main(String[] args) {
        Contest_3_1_ t = new Contest_3_1_();
        t.totalSteps(new int[]{5,14,15,2,11,5,13,15});
    }
}
