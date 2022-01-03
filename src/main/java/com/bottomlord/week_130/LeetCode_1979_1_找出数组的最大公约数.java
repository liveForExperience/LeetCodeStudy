package com.bottomlord.week_130;

/**
 * @author chen yue
 * @date 2022-01-03 19:22:05
 */
public class LeetCode_1979_1_找出数组的最大公约数 {
    public int findGCD(int[] nums) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        return gcd(max, min);
    }

    private int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }
}
