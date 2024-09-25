package com.bottomlord.week_064;

/**
 * @author ChenYue
 * @date 2020/9/21 8:29
 */
public class LeetCode_275_1_H指数II {
    public int hIndex(int[] citations) {
        int len = citations.length, i = 0;
        while (i < len && citations[len - 1 - i] > i) {
            i++;
        }

        return i;
    }
}
