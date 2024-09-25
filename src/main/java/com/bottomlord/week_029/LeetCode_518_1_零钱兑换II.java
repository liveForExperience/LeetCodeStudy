package com.bottomlord.week_029;

/**
 * @author ThinkPad
 * @date 2020/1/24 10:45
 */
public class LeetCode_518_1_零钱兑换II {
    private int ans = 0;
    public int change(int amount, int[] coins) {
        backTrace(amount, 0, 0, coins);
        return ans;
    }

    private void backTrace(int amount, int sum, int index, int[] coins) {
        if (sum > amount) {
            return;
        }

        if (sum == amount) {
            ans++;
            return;
        }

        for (int i = index; i < coins.length; i++) {
            backTrace(amount, sum + coins[i], i, coins);
        }
    }
}
