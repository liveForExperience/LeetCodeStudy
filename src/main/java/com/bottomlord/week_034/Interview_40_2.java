package com.bottomlord.week_034;

import java.util.Arrays;

/**
 * @author ThinkPad
 * @date 2020/2/28 12:47
 */
public class Interview_40_2 {
    public int[] getLeastNumbers(int[] arr, int k) {
        int[] ans = new int[k];
        Arrays.sort(arr);

        for (int i = 0; i < k; i++) {
            ans[i] = arr[i];
        }

        return ans;
    }
}
