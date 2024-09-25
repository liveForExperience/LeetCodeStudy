package com.bottomlord.contest_20220515;

/**
 * @author chen yue
 * @date 2022-05-15 10:24:18
 */
public class Contest_3_1_按位与结果大于零的最大组合 {
    public int largestCombination(int[] candidates) {
        int max = 0;
        for (int i = 0; i < 32; i++) {
            int count = 0, mask = 1 << i;
            for (int candidate : candidates) {
                if ((candidate & mask) != 0) {
                    count++;
                }
            }
            max = Math.max(count, max);
        }

        return max;
    }
}
