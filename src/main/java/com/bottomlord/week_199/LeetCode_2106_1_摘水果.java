package com.bottomlord.week_199;

/**
 * @author chen yue
 * @date 2023-05-04 21:58:35
 */
public class LeetCode_2106_1_摘水果 {
    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int ans = 0, sum = 0, n = fruits.length;
        for (int l = 0, r = 0; r < n; r++) {
            sum += fruits[r][1];
            while (l <= r && fruits[r][0] - fruits[l][0] + Math.min(Math.abs(fruits[r][0] - startPos), Math.abs(startPos - fruits[l][0])) > k) {
                sum -= fruits[l++][1];
            }

            ans = Math.max(ans, sum);
        }

        return ans;
    }
}
