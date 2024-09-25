package com.bottomlord.week_050;

/**
 * @author ChenYue
 * @date 2020/6/16 8:41
 */
public class LeetCode_44_2 {
    public boolean isMatch(String s, String p) {
        int si = 0, pi = 0, sStart = -1, pStart = -1, sLen = s.length(), pLen = p.length();
        while (si < sLen) {
            if (pi < pLen && (s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '?')) {
                si++;
                pi++;
            } else if (pi < pLen && p.charAt(pi) == '*') {
                sStart = si;
                pStart = pi++;
            } else if (sStart != -1) {
                si = ++sStart;
                pi = pStart + 1;
            } else {
                return false;
            }
        }

        while (pi < pLen && p.charAt(pi) == '*') {
            pi++;
        }

        return pi == pLen;
    }
}
