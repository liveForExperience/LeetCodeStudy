package com.bottomlord.week_032;

/**
 * @author ThinkPad
 * @date 2020/2/12 20:46
 */
public class LeetCode_201_2 {
    public int rangeBitwiseAnd(int m, int n) {
        int count = 0;
        while (m < n) {
            count++;
            m >>>= 1;
            n >>>= 1;
        }

        for (int i = 0; i < count; i++) {
            m <<= 1;
        }

        return m;
    }
}