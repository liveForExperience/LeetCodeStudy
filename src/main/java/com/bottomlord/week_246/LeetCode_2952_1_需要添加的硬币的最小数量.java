package com.bottomlord.week_246;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2024-03-30 14:35:11
 */
public class LeetCode_2952_1_需要添加的硬币的最小数量 {
    public int minimumAddedCoins(int[] coins, int target) {
        Arrays.sort(coins);
        int s = 1, ans = 0, i = 0, n = coins.length;
        while (s < target) {
            if (i < n && coins[i] <= s) {
                s += coins[i++];
            } else {
                ans++;
                s = s * 2;
            }
        }

        return ans;
    }
}
