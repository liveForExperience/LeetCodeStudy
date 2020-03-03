package com.bottomlord.week_035;

import java.util.Arrays;

/**
 * @author ThinkPad
 * @date 2020/3/3 8:05
 */
public class Interview_1001_1_合并排序的数组 {
    public void merge(int[] A, int m, int[] B, int n) {
        for (int i = m; i < m + n; i++) {
            A[i] = B[m - i];
        }

        Arrays.sort(A);
    }
}
