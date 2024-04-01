package com.bottomlord.week_246;

import com.bottomlord.LeetCodeUtils;

/**
 * @author chen yue
 * @date 2024-03-31 21:52:39
 */
public class LeetCode_2952_2 {
    public int minimumAddedCoins(int[] coins, int target) {
        int[] arr = new int[target + 1];
        for (int coin : coins) {
            arr[coin]++;
        }

        int ans = 0, s = 1, x = 0;
        while (s <= target) {
            if (x <= target && arr[x] == 0) {
                x++;
                continue;
            }

            if (x <= s) {
                s += x * arr[x];
                x++;
            } else {
                ans++;
                s <<= 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        LeetCode_2952_2 t = new LeetCode_2952_2();
        t.minimumAddedCoins(LeetCodeUtils.convertToIntArr("[1,4,10]"), 19);

    }
}