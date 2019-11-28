package com.bottomlord.week_021;

import java.util.Arrays;
import java.util.Comparator;

public class LeetCode_646_1_最长数对链 {
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, Comparator.comparingInt(o -> o[1]));
        int cur = pairs[0][1], count = 0;
        for (int i = 1; i < pairs.length; i++) {
            int num = pairs[i][0];
            if (num > cur) {
                count++;
                cur = pairs[i][1];
            }
        }
        return count;
    }
}
