package com.bottomlord.week_001;

/**
 * @author LiveForExperience
 * @date 2019/7/9 11:58
 */
public class LeetCode_709_1_转换成小写字母 {
    public String toLowerCase(String str) {
        char[] cs = str.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] >= 65 && cs[i] <= 90) {
                cs[i] += 32;
            }
        }
        return new String(cs);
    }
}
