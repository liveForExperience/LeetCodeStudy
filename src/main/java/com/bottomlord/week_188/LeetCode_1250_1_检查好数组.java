package com.bottomlord.week_188;

/**
 * @author chen yue
 * @date 2023-02-15 20:24:30
 */
public class LeetCode_1250_1_检查好数组 {
    public boolean isGoodArray(int[] nums) {
        int g = nums[0];
        for (int i = 1; i < nums.length; i++) {
            g = gcd(g, nums[i]);
        }

        return g == 1;
    }

    private int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }
}
