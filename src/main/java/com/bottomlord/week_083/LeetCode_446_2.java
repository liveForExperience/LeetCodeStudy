package com.bottomlord.week_083;

import java.util.HashMap;

/**
 * @author ChenYue
 * @date 2021/2/10 10:21
 */
public class LeetCode_446_2 {
    public int numberOfArithmeticSlices(int[] A) {
        int len = A.length, ans = 0;
        HashMap<Integer, Integer>[] dp = new HashMap[len];
        for (int i = 0; i < len; i++) {
            dp[i] = new HashMap<>();
            for (int j = 0; j < i; j++) {
                long delta = (long) A[i] - A[j];
                if (delta < Integer.MIN_VALUE || delta > Integer.MAX_VALUE) {
                    continue;
                }

                int diff = (int)delta;
                int pre = dp[j].getOrDefault(diff, 0), cur = dp[i].getOrDefault(diff, 0);
                dp[i].put(diff, pre + cur + 1);
                ans += pre;
            }
        }

        return ans;
    }
}
