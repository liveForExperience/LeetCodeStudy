package com.bottomlord.week_039;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author ChenYue
 * @date 2020/4/2 13:26
 */
public class Interview_0813_1_堆箱子 {
    public int pileBox(int[][] box) {
        Arrays.sort(box, (x, y) -> x[0] == y[0] ? x[1] == y[1] ? y[2] - x[2] : y[1] - x[1] : x[0] - y[0]);
        int[] dp = new int[box.length];
        dp[0] = box[0][2];
        int ans = dp[0];
        for (int i = 0; i < box.length; i++) {
            int depth = box[i][1], high = box[i][2];
            for (int j = 0; j < i; j++) {
                if (box[j][1] < depth && box[j][2] < high) {
                    dp[i] = Math.max(dp[i], dp[j] + high);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return dp[box.length - 1];
    }
}
