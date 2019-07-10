package com.bottomlord.week_1;

/**
 * @author LiveForExperience
 * @date 2019/7/10 13:03
 */
public class LeetCode_461_1_汉明距离 {
    public int hammingDistance(int x, int y) {
        int a = x ^ y;
        int count = 0;
        while (a != 0) {
            count++;
            a = a & (a - 1);
        }
        return count;
    }
}
