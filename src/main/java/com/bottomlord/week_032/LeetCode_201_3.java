package com.bottomlord.week_032;

/**
 * @author ThinkPad
 * @date 2020/2/12 22:04
 */
public class LeetCode_201_3 {
    public int rangeBitwiseAnd(int m, int n) {
        while (m < n) {
            n &= (n - 1);
        }

        return n;
    }
}