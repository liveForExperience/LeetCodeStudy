package com.bottomlord.week_117;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2021-10-07 12:43:25
 */
public class LeetCode_1779_1_找到最近的有相同的X或Y坐标的点 {
    public int nearestValidPoint(int x, int y, int[][] points) {
        int min = Integer.MAX_VALUE, n = points.length;
        int[] arr = new int[n];
        Arrays.fill(arr, -1);
        for (int i = 0; i < points.length; i++) {
            int[] point = points[i];
            if (point[0] == x || point[1] == y) {
                arr[i] = Math.abs(x - point[0]) + Math.abs(y - point[1]);
                min = Math.min(min, arr[i]);
            }
        }

        if (min == Integer.MIN_VALUE) {
            return -1;
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == min) {
                return i;
            }
        }

        return -1;
    }
}
