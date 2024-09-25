package com.bottomlord.week_229;

/**
 * @author chen yue
 * @date 2023-12-02 14:10:52
 */
public class LeetCode_1094_1_拼车 {
    public boolean carPooling(int[][] trips, int capacity) {
        int[] arr = new int[1001];
        for (int[] trip : trips) {
            arr[trip[1]] += trip[0];
            arr[trip[2]] -= trip[0];
        }

        int cur = 0;
        for (int n : arr) {
            cur += n;
            if (cur > capacity) {
                return false;
            }
        }

        return true;
    }
}
