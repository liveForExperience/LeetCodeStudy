package com.bottomlord.week_8;

public class LeetCode_1033_1_移动石子直到连续 {
    public int[] numMovesStones(int a, int b, int c) {
        int max = Math.max(a, Math.max(b, c));
        int min = Math.min(a, Math.min(b, c));
        int mid = a + b + c - max - min;

        int leftDiff = max - mid;
        int rightDiff = mid - min;
        if (leftDiff == 1 && leftDiff == rightDiff) {
            return new int[2];
        }

        int diff = Math.min(leftDiff, rightDiff);

        return new int[]{diff <= 2 ? 1: 2, max - min - 2};
    }
}
