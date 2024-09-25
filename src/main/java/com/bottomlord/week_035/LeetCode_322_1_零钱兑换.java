package com.bottomlord.week_035;

/**
 * @author ThinkPad
 * @date 2020/3/8 9:25
 */
public class LeetCode_322_1_零钱兑换 {
    public int coinChange(int[] coins, int amount) {
        if (amount < 1) {
            return 0;
        }

        return dfs(coins, amount, new int[amount + 1]);
    }

    private int dfs(int[] coins, int amount, int[] mem) {
        if (amount < 0) {
            return -1;
        }

        if (amount == 0) {
            return 0;
        }

        if (mem[amount] != 0) {
            return mem[amount];
        }

        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int count = dfs(coins, amount - coin, mem);
            if (count != -1) {
                min = Math.min(count + 1, min);
            }
        }

        mem[amount] = min == Integer.MAX_VALUE ? -1 : min;
        return mem[amount];
    }
}
