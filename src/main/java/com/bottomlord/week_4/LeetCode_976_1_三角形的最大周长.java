package com.bottomlord.week_4;

import java.util.Arrays;

/**
 * @author LiveForExperience
 * @date 2019/8/1 9:52
 */
public class LeetCode_976_1_三角形的最大周长 {
    public int largestPerimeter(int[] A) {
        Arrays.sort(A);
        for (int i = A.length - 1; i >= 2; i--) {
            if (A[i - 2] + A[i - 1] > A[i]) {
                return A[i - 2] + A[i - 1] + A[i];
            }
        }
        return 0;
    }
}
