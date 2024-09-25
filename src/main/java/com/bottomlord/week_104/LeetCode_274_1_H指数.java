package com.bottomlord.week_104;

import java.util.Arrays;

public class LeetCode_274_1_H指数 {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int h = 0, i = citations.length - 1;
        while (i >= 0 && citations[i] > h) {
            h++;
            i--;
        }
        return h;
    }
}
