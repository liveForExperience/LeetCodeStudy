package com.bottomlord.week_032;

/**
 * @author ThinkPad
 * @date 2020/2/13 17:16
 */
public class LeetCode_201_5 {
    public int rangeBitwiseAnd(int m, int n) {
        return m == n ? m : m & -Integer.highestOneBit(m ^ n);
    }
}
