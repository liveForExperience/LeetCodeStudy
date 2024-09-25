package com.bottomlord.week_003;

/**
 * @author LiveForExperience
 * @date 2019/7/22 13:33
 */
public class LeetCode_521_1_最长特殊序列I {
    public int findLUSlength(String a, String b) {
        if (a.equals(b)) {
            return -1;
        }
        return a.length() > b.length() ? a.length() : b.length();
    }
}
