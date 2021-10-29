package com.bottomlord.week_120;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2021-10-28 23:24:39
 */
public class LeetCode_869_2 {
    public boolean reorderedPowerOf2(int n) {
        int[] arr = initArr(n);
        for (int i = 0; i < 31; i++) {
            if (Arrays.equals(arr, initArr(1 << i))) {
                return true;
            }
        }

        return false;
    }

    private int[] initArr(int n) {
        int[] arr = new int[10];
        while (n != 0) {
            arr[n % 10]++;
            n /= 10;
        }
        return arr;
    }
}
