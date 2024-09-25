package com.bottomlord.week_212;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2023-08-01 15:16:00
 */
public class LeetCode_2681_1_英雄的力量 {
    public int sumOfPower(int[] nums) {
        long dp = 0, ans = 0, mod = 1000000007;
        Arrays.sort(nums);
        for (int num : nums) {
            ans = (ans + (long) num * num % mod * (num + dp)) % mod;
            dp = (2 * dp + num) % mod;
        }

        return (int)ans;
    }
}
