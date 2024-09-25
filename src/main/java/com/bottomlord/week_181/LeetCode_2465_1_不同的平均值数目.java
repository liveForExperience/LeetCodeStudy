package com.bottomlord.week_181;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chen yue
 * @date 2022-12-31 14:17:33
 */
public class LeetCode_2465_1_不同的平均值数目 {
    public int distinctAverages(int[] nums) {
        int n = nums.length, deleted = 0;
        Set<Double> set = new HashSet<>();
        boolean[] bucket = new boolean[n];

        while (deleted < n) {
            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE,
                minIndex = -1, maxIndex = -1;
            for (int i = 0; i < n; i++) {
                if (bucket[i]) {
                    continue;
                }

                int num = nums[i];
                if (num < min) {
                    min = num;
                    minIndex = i;
                }

                if (num > max) {
                    max = num;
                    maxIndex = i;
                }
            }

            bucket[maxIndex] = true;
            bucket[minIndex] = true;
            set.add(1D * (max + min) / 2);
            deleted += 2;
        }

        return set.size();
    }
}
