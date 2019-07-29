package com.bottomlord.week_4;

/**
 * @author LiveForExperience
 * @date 2019/7/29 22:35
 */
public class LeetCode_389_3 {
    public char findTheDifference(String s, String t) {
        int ans = 0;

        for (char c : s.toCharArray()) {
            ans ^= c;
        }

        for (char c : t.toCharArray()) {
            ans ^= c;
        }

        return (char) ans;
    }
}
