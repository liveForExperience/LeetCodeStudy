package com.bottomlord.week_206;

/**
 * @author chen yue
 * @date 2023-06-19 10:01:37
 */
public class LeetCode_1262_2 {
    public int maxSumDivThree(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n + 1][3];
        dp[0][1] = dp[0][2] = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 3; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][(j - nums[i - 1] % 3 + 3) % 3] + nums[i - 1]);
            }
        }
        return dp[n][0];
    }

    public static void main(String[] args) {
        LeetCode_1262_2 t = new LeetCode_1262_2();
        t.maxSumDivThree(new int[]{3,6,5,1,8});
    }
}