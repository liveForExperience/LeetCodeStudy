package com.bottomlord.week_113;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2021-09-06 19:25:26
 */
public class LeetCode_1619_1_删除某些元素后的数组均值 {
    public double trimMean(int[] arr) {
        Arrays.sort(arr);
        int len = arr.length, skip = len / 20, sum = 0, count = 0;

        for (int i = skip; i < len - skip; i++) {
            sum += arr[i];
            count++;
        }

        return 1D * sum / count;
    }
}
