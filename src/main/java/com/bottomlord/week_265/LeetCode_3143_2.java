package com.bottomlord.week_265;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2024-08-04 11:10:00
 */
public class LeetCode_3143_2 {
    public int maxPointsInsideSquare(int[][] points, String s) {
        int[] arr = new int[26];
        Arrays.fill(arr, Integer.MAX_VALUE);
        int dupMin = Integer.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            int[] point = points[i];
            int len = Math.max(Math.abs(point[0]), Math.abs(point[1])),
                index = s.charAt(i) - 'a';

            if (len < arr[index]) {
                dupMin = Math.min(dupMin, arr[index]);
                arr[index] = len;
            } else if (len < dupMin) {
                dupMin = len;
            }
        }

        int cnt = 0;
        for (int len : arr) {
            if (len < dupMin) {
                cnt++;
            }
        }
        return cnt;
    }
}
