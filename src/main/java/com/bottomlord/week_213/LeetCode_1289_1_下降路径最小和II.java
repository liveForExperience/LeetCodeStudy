package com.bottomlord.week_213;

/**
 * @author chen yue
 * @date 2023-08-10 09:27:38
 */
public class LeetCode_1289_1_下降路径最小和II {
    public int minFallingPathSum(int[][] grid) {
        int n = grid[0].length;
        int[] dp = grid[0];

        for (int i = 1; i < grid.length; i++) {
            int[] arr = new int[n];
            for (int j = 0; j < n; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = 0; k < n; k++) {
                    if (j == k) {
                        continue;
                    }

                    min = Math.min(min, dp[k]);
                }
                arr[j] = min + grid[i][j];
            }
            dp = arr;
        }

        int ans = Integer.MAX_VALUE;
        for (int num : dp) {
            ans = Math.min(num, ans);
        }
        return ans;
    }

    public static void main(String[] args) {
        LeetCode_1289_1_下降路径最小和II t = new LeetCode_1289_1_下降路径最小和II();
        t.minFallingPathSum(new int[][]{{1,2,3}, {4,5,6}, {7,8,9}});
    }
}
