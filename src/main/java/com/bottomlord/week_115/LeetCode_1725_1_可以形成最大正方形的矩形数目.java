package com.bottomlord.week_115;

/**
 * @author chen yue
 * @date 2021-09-26 16:34:38
 */
public class LeetCode_1725_1_可以形成最大正方形的矩形数目 {
    public int countGoodRectangles(int[][] rectangles) {
        int max = Integer.MIN_VALUE, count = 0;
        for (int[] arr : rectangles) {
            int min = Math.min(arr[0], arr[1]);

            if (min > max) {
                count = 1;
                max = min;
            } else if (min == max) {
                count++;
            }
        }

        return count;
    }
}
