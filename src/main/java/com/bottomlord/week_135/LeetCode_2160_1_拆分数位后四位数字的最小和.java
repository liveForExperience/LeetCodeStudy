package com.bottomlord.week_135;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2022-02-07 21:48:19
 */
public class LeetCode_2160_1_拆分数位后四位数字的最小和 {
    public int minimumSum(int num) {
        int[] arr = new int[4];
        int index = 0;
        while (num > 0) {
            arr[index++] = num % 10;
            num /= 10;
        }
        Arrays.sort(arr);
        return arr[0] * 10 + arr[2] + arr[1] * 10 + arr[3];
    }
}
