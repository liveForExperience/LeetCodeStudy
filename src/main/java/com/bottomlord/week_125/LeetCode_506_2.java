package com.bottomlord.week_125;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2021-12-02 08:59:45
 */
public class LeetCode_506_2 {
    public String[] findRelativeRanks(int[] score) {
        int max = Arrays.stream(score).max().getAsInt();
        Integer[] bucket = new Integer[max + 1];
        for (int i = 0; i < score.length; i++) {
            bucket[score[i]] = i;
        }

        int count = 0;
        String[] ans = new String[score.length];
        for (int i = bucket.length - 1; i >= 0; i--) {
            Integer index = bucket[i];

            if (index == null) {
                continue;
            }

            count++;
            ans[index] = count > 3 ? "" + count : count == 1 ? "Gold Medal" : count == 2 ? "Silver Medal" : "Bronze Medal";
        }

        return ans;
    }
}
