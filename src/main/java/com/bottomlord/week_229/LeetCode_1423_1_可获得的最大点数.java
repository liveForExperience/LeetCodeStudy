package com.bottomlord.week_229;

/**
 * @author chen yue
 * @date 2023-12-03 14:51:06
 */
public class LeetCode_1423_1_可获得的最大点数 {
    public int maxScore(int[] cardPoints, int k) {
        int ans = 0, n = cardPoints.length;
        for (int i = 0; i < k; i++) {
            ans += cardPoints[i];
        }

        if (n == k) {
            return ans;
        }

        int sum = ans;
        for (int i = 0; i < k; i++) {
            sum = sum - cardPoints[k - 1 - i] + cardPoints[n - 1 - i];
            ans = Math.max(ans, sum);
        }

        return ans;
    }
}
