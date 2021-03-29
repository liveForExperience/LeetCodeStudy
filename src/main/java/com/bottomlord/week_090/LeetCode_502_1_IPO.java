package com.bottomlord.week_090;

/**
 * @author ChenYue
 * @date 2021/3/26 8:27
 */
public class LeetCode_502_1_IPO {
    private int ans;

    public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        this.ans = W;
        k = Math.min(k, Profits.length);
        backTrack(k, W, Profits, Capital, new boolean[Profits.length]);
        return ans;
    }

    private void backTrack(int time, int w, int[] profits, int[] capital, boolean[] memo) {
        ans = Math.max(ans, w);
        if (time == 0) {
            return;
        }

        for (int i = 0; i < profits.length; i++) {
            if (memo[i] || w < capital[i]) {
                continue;
            }

            memo[i] = true;
            backTrack(time - 1, w + profits[i], profits, capital, memo);
            memo[i] = false;
        }
    }
}
