package com.bottomlord.week_102;

/**
 * @author ChenYue
 * @date 2021/6/23 8:18
 */
public class LeetCode_offer15_1_二进制中1的个数 {
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n &= n -1;
            count++;
        }
        return count;
    }
}
