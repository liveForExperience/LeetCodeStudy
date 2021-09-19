package com.bottomlord.week_114;

/**
 * @author chen yue
 * @date 2021-09-18 19:33:27
 */
public class LeetCode_1672_1_最富有客户的资产总量 {
    public int maximumWealth(int[][] accounts) {
        int len = accounts.length, max = 0;
        int[] sums=  new int[len];

        for (int i = 0; i < accounts.length; i++) {
            for (int j = 0; j < accounts[i].length; j++) {
                sums[i] += accounts[i][j];
            }

            max = Math.max(max, sums[i]);
        }

        return max;
    }
}
