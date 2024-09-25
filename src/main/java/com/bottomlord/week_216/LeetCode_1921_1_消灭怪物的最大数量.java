package com.bottomlord.week_216;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2023-09-03 10:14:18
 */
public class LeetCode_1921_1_消灭怪物的最大数量 {
    public int eliminateMaximum(int[] dist, int[] speed) {
        int n = dist.length;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (dist[i] - 1) / speed[i] + 1;
        }

        Arrays.sort(arr);
        for (int i = 0; i < n; i++) {
            if (i >= arr[i]) {
                return i;
            }
        }

        return n;
    }
}
