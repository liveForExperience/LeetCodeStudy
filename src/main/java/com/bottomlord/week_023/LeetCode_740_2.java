package com.bottomlord.week_023;

public class LeetCode_740_2 {
    public int deleteAndEarn(int[] nums) {
        int[] bucket = new int[10001];
        for (int num : nums) {
            bucket[num]++;
        }

        int take = 0, avoid = 0, pre = -1;
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] > 0) {
                int max = Math.max(take, avoid);
                if (i - 1 == pre) {
                    take = avoid + bucket[i] * i;
                } else {
                    take = max + bucket[i] * i;
                }
                avoid = max;

                pre = i;
            }
        }

        return Math.max(take, avoid);
    }
}
