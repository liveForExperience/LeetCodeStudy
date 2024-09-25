package com.bottomlord.week_004;

import java.util.Arrays;

/**
 * @author LiveForExperience
 * @date 2019/8/3 10:38
 */
public class LeetCode_1089_1_复写零 {
    public void duplicateZeros(int[] arr) {
        int[] copy = Arrays.copyOf(arr, arr.length);
        for (int i = 0, j = 0; j < arr.length; i++) {
            if (copy[i] == 0) {
                arr[j++] = 0;
            }

            arr[j++] = copy[i];
        }
    }
}
