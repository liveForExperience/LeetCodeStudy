package com.bottomlord.week_076;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2020/12/22 8:50
 */
public class LeetCode_396_1_旋转函数 {
    public int maxRotateFunction(int[] A) {
        int len = A.length, sum = Arrays.stream(A).sum(), f = 0;
        for (int i = 0; i < len; i++) {
            f += A[i] * i;
        }

        int max = f;
        for (int i = len - 1; i > 0; i--) {
            f = f + sum - (len) * A[i];
            max = Math.max(max, f);
        }

        return max;
    }
}
