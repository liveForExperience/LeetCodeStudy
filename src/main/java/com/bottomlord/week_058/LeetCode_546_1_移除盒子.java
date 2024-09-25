package com.bottomlord.week_058;

/**
 * @author ChenYue
 * @date 2020/8/15 11:02
 */
public class LeetCode_546_1_移除盒子 {
    public int removeBoxes(int[] boxes) {
        int len = boxes.length;
        return recurse(boxes, new int[len][len][len], 0, len - 1, 0);
    }

    private int recurse(int[] boxes, int[][][] dp, int l, int r, int k) {
        if (l > r) {
            return 0;
        }

        if (dp[l][r][k] != 0) {
            return dp[l][r][k];
        }

        while (r > l && boxes[r] == boxes[r - 1]) {
            r--;
            k++;
        }

        dp[l][r][k] = recurse(boxes, dp, l, r - 1, 0) + (k + 1) * (k + 1);

        for (int i = l; i < r; i++) {
            if (boxes[i] == boxes[r]) {
                dp[l][r][k] = Math.max(dp[l][r][k], recurse(boxes, dp, l, i, k + 1) + recurse(boxes, dp, i + 1, r - 1, 0));
            }
        }

        return dp[l][r][k];
    }
}
