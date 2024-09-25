package com.bottomlord.week_192;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2023-03-19 15:44:22
 */
public class LeetCode_935_1_骑士拨号器 {
    public int knightDialer(int n) {
        Map<Integer, int[]> map = new HashMap<>(10);
        map.put(0, new int[]{4, 6});
        map.put(1, new int[]{6, 8});
        map.put(2, new int[]{7, 9});
        map.put(3, new int[]{4, 8});
        map.put(4, new int[]{0, 3, 9});
        map.put(6, new int[]{0, 1, 7});
        map.put(7, new int[]{2, 6});
        map.put(8, new int[]{1, 3});
        map.put(9, new int[]{2, 4});

        int mod = (int) 1e9 + 7;

        int[][] dp = new int[n + 1][10];
        for (int i = 0; i < 10; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
                dp[i][entry.getKey()] = (sum(map, entry.getKey(), dp, i - 1, mod)) % mod;
            }
        }

        int ans = 0;
        for (int i = 0; i <= 9; i++) {
            ans = (ans + dp[n][i]) % mod;
        }

        return ans;
    }

    private int sum(Map<Integer, int[]> map, int key, int[][] dp, int n, int mod) {
        int[] values = map.get(key);
        int sum = 0;
        for (int value : values) {
            sum = (sum + dp[n][value]) % mod;
        }
        return sum;
    }
}
