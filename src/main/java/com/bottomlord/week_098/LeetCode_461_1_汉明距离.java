package com.bottomlord.week_098;

/**
 * @author ChenYue
 * @date 2021/5/27 8:16
 */
public class LeetCode_461_1_汉明距离 {
    public int hammingDistance(int x, int y) {
        int xor = x ^ y;
        int count = 0;
        while (xor != 0) {
            count++;
            xor = xor & (xor - 1);
        }

        return count;
    }
}
