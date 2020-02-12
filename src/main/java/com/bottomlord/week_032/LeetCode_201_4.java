package com.bottomlord.week_032;

/**
 * @author ThinkPad
 * @date 2020/2/12 22:17
 */
public class LeetCode_201_4 {
    public int rangeBitwiseAnd(int m, int n) {
        if (m == n) {
            return m;
        }

        return m & ~Integer.highestOneBit(m ^ n) + 1;
    }
}