package com.bottomlord.week_103;

/**
 * @author ChenYue
 * @date 2021/7/4 21:57
 */
public class LeetCode_645_1_错误的集合 {
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        int[] bucket = new int[n + 1];
        for (int i = 0; i < n; i++) {
            bucket[nums[i]]++;
        }

        int[] ans = new int[2];
        for (int i = 1; i < n + 1; i++) {
            if (bucket[i] == 0) {
                ans[1] = i;
            }

            if (bucket[i] == 2) {
                ans[0] = i;
            }
        }

        return ans;
    }
}
