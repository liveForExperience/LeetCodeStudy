package com.bottomlord.week_068;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2020/10/28 8:49
 */
public class LeetCode_319_1_灯泡开关 {
    public int bulbSwitch(int n) {
        if (n <= 1) {
            return n;
        }

        boolean[] flags = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (j % i == 0) {
                    flags[j] = !flags[j];
                }
            }
        }

        int ans = 0;
        for (boolean flag : flags) {
            ans += flag ? 1 : 0;
        }
        return ans;
    }
}
