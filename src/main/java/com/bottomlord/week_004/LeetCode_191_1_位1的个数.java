package com.bottomlord.week_004;

/**
 * @author LiveForExperience
 * @date 2019/7/31 13:00
 */
public class LeetCode_191_1_位1的个数 {
    public int hammingWeight(int n) {
        String oldStr = Integer.toBinaryString(n);
        int oldLen = oldStr.length();
        String str = oldStr.replaceAll("1", "");
        return oldLen - str.length();
    }
}
