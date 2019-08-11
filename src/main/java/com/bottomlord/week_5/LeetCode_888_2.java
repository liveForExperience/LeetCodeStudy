package com.bottomlord.week_5;

import java.util.HashSet;
import java.util.Set;

public class LeetCode_888_2 {
    public int[] fairCandySwap(int[] A, int[] B) {
        int maxA = Integer.MIN_VALUE, maxB = Integer.MIN_VALUE, sumA = 0, sumB = 0;

        for (int num : A) {
            maxA = Math.max(maxA, num);
            sumA += num;
        }

        for (int num : B) {
            maxB = Math.max(maxB, num);
            sumB += num;
        }

        boolean[] bucketA = new boolean[maxA + 1];
        boolean[] bucketB = new boolean[maxB + 1];

        for (int num : A) {
            bucketA[num] = true;
        }

        for (int num : B) {
            bucketB[num] = true;
        }

        int diff = (sumA - sumB) / 2;

        for (int i = 0; i < bucketA.length; i++) {
            if (i - diff >= 0 && bucketA[i] && bucketB[i - diff]) {
                return new int[]{i, i - diff};
            }
        }

        return new int[2];
    }
}