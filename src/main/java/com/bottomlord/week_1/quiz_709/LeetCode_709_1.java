package com.bottomlord.week_1.quiz_709;

/**
 * @author LiveForExperience
 * @date 2019/7/9 11:58
 */
public class LeetCode_709_1 {
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
