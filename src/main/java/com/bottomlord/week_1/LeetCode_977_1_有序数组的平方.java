package com.bottomlord.week_1;

import java.util.Arrays;

/**
 * @author LiveForExperience
 * @date 2019/7/11 9:12
 */
public class LeetCode_977_1_有序数组的平方 {
    public int[] sortedSquares(int[] A) {
        for (int i = 0; i < A.length; i++) {
            A[i] *= A[i];
        }

        Arrays.sort(A);
        return A;
    }
}
