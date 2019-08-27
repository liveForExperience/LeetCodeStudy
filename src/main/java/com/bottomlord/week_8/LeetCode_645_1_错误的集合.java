package com.bottomlord.week_8;

public class LeetCode_645_1_错误的集合 {
    public int[] findErrorNums(int[] nums) {
        int[] bucket = new int[nums.length + 1];
        for (int num : nums) {
            bucket[num]++;
        }

        int[] ans = new int[2];
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] == 0) {
                ans[1] = i;
            }

            if (bucket[i] == 2) {
                ans[0] = i;
            }

            if (ans[0] != 0 && ans[1] != 0) {
                break;
            }
        }

        return ans;
    }
}
