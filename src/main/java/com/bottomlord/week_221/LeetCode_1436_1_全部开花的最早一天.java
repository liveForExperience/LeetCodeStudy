package com.bottomlord.week_221;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2023-10-15 14:07:12
 */
public class LeetCode_1436_1_全部开花的最早一天 {
    public int earliestFullBloom(int[] plantTime, int[] growTime) {
        int preTime = 0, totalTime = 0, n = plantTime.length;
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }

        Arrays.sort(arr, (x, y) -> growTime[y] - growTime[x]);

        for (Integer index : arr) {
            totalTime = preTime + growTime[index];
            preTime += plantTime[index];
        }

        return totalTime;
    }
}
