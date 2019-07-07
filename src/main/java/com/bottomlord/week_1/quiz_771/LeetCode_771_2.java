package com.bottomlord.week_1.quiz_771;

/**
 * @author LiveForExperience
 * @date 2019/7/7 19:42
 */
public class LeetCode_771_2 {
    public int numJewelsInStones(String J, String S) {
        String[] jss = J.split("");
        int len = S.length();
        for (String s: jss) {
            S = S.replace(s, "");
        }

        return len - S.length();
    }
}
