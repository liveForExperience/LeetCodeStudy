package com.bottomlord.week_029;

import java.util.Arrays;

/**
 * @author ThinkPad
 * @date 2020/1/21 14:38
 */
public class LeetCode_869_2 {
    public boolean reorderedPowerOf2(int N) {
        int[] arr = count(N);
        for (int i = 0; i < 31; i++) {
            if (Arrays.equals(arr, count(1 << i))) {
                return true;
            }
        }

        return false;
    }

    private int[] count(int num) {
        int[] arr = new int[10];
        while (num > 0) {
            arr[num % 10]++;
            num /= 10;
        }
        return arr;
    }
}