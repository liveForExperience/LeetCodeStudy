package com.bottomlord.week_125;

/**
 * @author chen yue
 * @date 2021-12-03 08:49:47
 */
public class LeetCode_1005_1_K次取反后最大化的数组和 {
    public int largestSumAfterKNegations(int[] nums, int k) {
        int[] bucket = new int[201];
        for (int num : nums) {
            bucket[num + 100]++;
        }

        for (int i = 0; i < 101 && k > 0; i++) {
            if (bucket[i] == 0) {
                continue;
            }

            while (bucket[i] > 0 && k > 0) {
                bucket[i]--;
                bucket[200 - i]++;
                k--;
            }
        }

        int sum = 0;
        if (k % 2 == 1) {
            for (int i = 100; i < bucket.length; i++) {
                if (bucket[i] != 0) {
                    bucket[i]--;
                    sum -= (i - 100);
                    break;
                }
            }
        }

        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] == 0) {
                continue;
            }

            int time = bucket[i];
            sum += time * (i - 100);
        }

        return sum;
    }
}
