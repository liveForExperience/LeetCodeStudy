package com.bottomlord.week_056;

/**
 * @author ChenYue
 * @date 2020/7/27 8:21
 */
public class LeetCode_392_1_判断子序列 {
    public boolean isSubsequence(String s, String t) {
        int si = 0, sl = s.length(), ti = 0, tl = t.length();
        while (si < sl && ti < tl) {
            if (s.charAt(si) == t.charAt(ti)) {
                si++;
                ti++;
            } else {
                ti++;
            }
        }

        return si == sl && ti < tl;
    }
}
