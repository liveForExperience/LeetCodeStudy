package com.bottomlord.week_188;

/**
 * @author chen yue
 * @date 2023-02-14 10:57:35
 */
public class LeetCode_1124_1_表现良好的最长时间段 {
    public int longestWPI(int[] hours) {
        int n = hours.length;
        int[] status = new int[n];
        for (int i = 0; i < n; i++) {
            status[i] = hours[i] > 8 ? 1 : -1;
        }

        int[] sums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + status[i - 1];
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= 0; j--) {
                if (sums[j + 1] - sums[i] > 0) {
                    ans = Math.max(ans, j - i + 1);
                    break;
                }
            }
        }

        return ans;
    }
}
