package com.bottomlord.week_082;

/**
 * @author ChenYue
 * @date 2021/2/6 14:36
 */
public class LeetCode_1423_2 {
    public int maxScore(int[] cardPoints, int k) {
        int len = cardPoints.length, wLen = len - k, sum = 0;
        for (int cardPoint : cardPoints) {
            sum += cardPoint;
        }

        if (wLen == 0) {
            return sum;
        }

        int wSum = 0;
        for (int i = 0; i < wLen; i++) {
            wSum += cardPoints[i];
        }

        int min = wSum;
        for (int i = 1; i < len - wLen + 1; i++) {
            wSum = wSum - cardPoints[i - 1] + cardPoints[i + wLen - 1];
            min = Math.min(min, wSum);
        }
        return sum - min;
    }
}
