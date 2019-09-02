package com.bottomlord.week_004;

/**
 * @author LiveForExperience
 * @date 2019/7/31 13:05
 */
public class LeetCode_191_2 {
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n &= (n - 1);
            count++;
        }
        return count;
    }
}