package com.bottomlord.week_084;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2021/2/18 8:23
 */
public class LeetCode_995_1_K连续位的最小翻转次数 {
    public int minKBitFlips(int[] A, int K) {
        int len = A.length, count = 0;

        for (int i = 0; i <= len - K; i++) {
            if (A[i] == 1) {
                continue;
            }

            for (int j = i; j < i + K; j++) {
                A[j] ^= 1;
            }
            count++;
        }

        return Arrays.stream(A).sum() == len ? count : -1;
    }
}
