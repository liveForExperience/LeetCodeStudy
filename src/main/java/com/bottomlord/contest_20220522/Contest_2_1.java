package com.bottomlord.contest_20220522;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2022-05-22 10:28:10
 */
public class Contest_2_1 {
    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int n = capacity.length;
        int[] bucket = new int[n];
        for (int i = 0; i < n; i++) {
            bucket[i] = capacity[i] - rocks[i];
        }

        Arrays.sort(bucket);
        int count = 0;
        for (int num : bucket) {
            if (num == 0) {
                count++;
                continue;
            }

            if (num <= additionalRocks) {
                count++;
                additionalRocks -= num;
                continue;
            }

            if (additionalRocks <= 0) {
                break;
            }
        }

        return count;
    }
}
