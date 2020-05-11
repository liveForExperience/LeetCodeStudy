package com.bottomlord.week_045;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2020/5/11 8:18
 */
public class Interview_1714_1_最小K个数 {
    public int[] smallestK(int[] arr, int k) {
        if (k == 0) {
            return new int[0];
        }

        Arrays.sort(arr);
        int[] ans = new int[k];
        System.arraycopy(arr, 0, ans, 0, k);
        return ans;
    }
}
