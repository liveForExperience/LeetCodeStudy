package com.bottomlord.week_099;

/**
 * @author ChenYue
 * @date 2021/6/1 9:27
 */
public class LeetCode_1744_1_你能在你最喜欢的那天吃到你最喜欢的糖果吗 {
    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        int len = queries.length, type = candiesCount.length;
        boolean[] ans = new boolean[len];
        long[] sums = new long[type + 1];
        for (int i = 1; i < sums.length; i++) {
            sums[i] = sums[i - 1] + candiesCount[i - 1];
        }

        for (int i = 0; i < queries.length; i++) {
            int t = queries[i][0], d = queries[i][1] + 1, c = queries[i][2];
            long l = sums[t] / c + 1, r = sums[t + 1];
            ans[i] = d >= l && d <= r;
        }

        return ans;
    }
}
