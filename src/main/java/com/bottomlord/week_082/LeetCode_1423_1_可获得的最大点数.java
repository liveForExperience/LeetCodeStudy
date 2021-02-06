package com.bottomlord.week_082;

/**
 * @author ChenYue
 * @date 2021/2/6 11:16
 */
public class LeetCode_1423_1_可获得的最大点数 {
    public int maxScore(int[] cardPoints, int k) {
        int len = cardPoints.length;
        int[] sums = new int[len + 1];
        sums[1] = cardPoints[0];
        for (int i = 2; i < len + 1; i++) {
            sums[i] = sums[i - 1] + cardPoints[i - 1];
        }

        int max = 0;
        for (int i = 0; i <= k; i++) {
            int left = i, right = len - k + i;
            int cur = sums[left] - sums[0] + sums[len] - sums[right];
            max = Math.max(max, cur);
        }
        return max;
    }
}
