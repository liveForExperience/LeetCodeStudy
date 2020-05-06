package com.bottomlord.week_043;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2020/4/30 11:14
 */
public class Interview_1708_1_马戏团人塔 {
    public int bestSeqAtIndex(int[] height, int[] weight) {
        int len = height.length;
        int[][] persons = new int[len][2];
        for (int i = 0; i < len; i++) {
            persons[i][0] = height[i];
            persons[i][1] = weight[i];
        }
        Arrays.sort(persons, (x1, x2) -> x1[0] == x2[0] ? x2[1] - x1[1] : x1[0] - x2[0]);

        int[] dp = new int[len];
        int res = 0;
        for (int[] person : persons) {
            int i = Arrays.binarySearch(dp, 0, res, person[1]);

            if (i < 0) {
                i = -(i + 1);
            }

            dp[i] = person[1];

            if (i == res) {
                res++;
            }
        }

        return res;
    }
}
