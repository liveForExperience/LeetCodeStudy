package com.bottomlord.week_224;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2023-10-27 14:24:54
 */
public class LeetCode_1465_1_切割后面积最大的蛋糕 {
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        return (int) ((long) calMax(horizontalCuts, h) * calMax(verticalCuts, w) % 1000000007);
    }

    public int calMax(int[] arr, int board) {
        int res = 0, pre = 0;
        for (int cur : arr) {
            res = Math.max(cur - pre, res);
            pre = cur;
        }
        return Math.max(res, board - pre);
    }
}
