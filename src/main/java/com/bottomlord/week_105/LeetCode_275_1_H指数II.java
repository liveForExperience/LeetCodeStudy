package com.bottomlord.week_105;

/**
 * @author ChenYue
 * @date 2021/7/12 8:19
 */
public class LeetCode_275_1_H指数II {
    public int hIndex(int[] citations) {
        int h = 0, i = citations.length - 1;
        while (i >= 0 && citations[i] > h) {
            h++;
            i--;
        }
        return h;
    }
}
