package com.bottomlord.week_125;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2021-12-02 08:43:19
 */
public class LeetCode_506_1_相对名次 {
    public String[] findRelativeRanks(int[] score) {
        int len = score.length;
        Integer[] arr = new Integer[len];
        for (int i = 0; i < len; i++) {
            arr[i] = i;
        }

        String[] ans = new String[len];
        Arrays.sort(arr, (x, y) -> score[y] - score[x]);

        for (int i = 0; i < len; i++) {
            if (i == 0) {
                ans[arr[i]] = "Gold Medal";
            } else if (i == 1) {
                ans[arr[i]] = "Silver Medal";
            } else if (i == 2) {
                ans[arr[i]] = "Bronze Medal";
            } else {
                ans[arr[i]] = i + 1 + "";
            }
        }

        return ans;
    }
}
