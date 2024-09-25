package com.bottomlord.week_090;

/**
 * @author ChenYue
 * @date 2021/3/29 8:33
 */
public class LeetCode_190_1_颠倒二进制位 {
    public int reverseBits(int n) {
        int mask = 1, time = 31;
        while (time-- > 0) {
            mask <<= 1;
        }

        int ans = 0, count = 1;
        time = 32;
        while (time-- > 0) {
            if ((count | n) == n) {
                ans |= mask;
            }
            mask >>= 1;
            count <<= 1;
        }
        return ans;
    }
}
