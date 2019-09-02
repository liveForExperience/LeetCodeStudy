package com.bottomlord.week_004;

import java.util.Arrays;

/**
 * @author LiveForExperience
 * @date 2019/7/29 22:23
 */
public class LeetCode_389_1_找不同 {
    public char findTheDifference(String s, String t) {
        char[] ss = s.toCharArray();
        char[] ts = t.toCharArray();

        Arrays.sort(ss);
        Arrays.sort(ts);

        for (int i = 0; i < ss.length; i++) {
            if (ss[i] != ts[i]) {
                return ts[i];
            }
        }

        return ts[ts.length - 1];
    }
}
