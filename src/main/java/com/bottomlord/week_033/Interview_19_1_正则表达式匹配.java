package com.bottomlord.week_033;

/**
 * @author ThinkPad
 * @date 2020/2/19 12:54
 */
public class Interview_19_1_正则表达式匹配 {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null || (p.length() == 0 && s.length() > 0)) {
            return false;
        }

        return backTrack(s, 0, p, 0);
    }

    private boolean backTrack(String s, int si, String p, int pi) {
        if (si >= s.length() && pi >= p.length()) {
            return true;
        }

        if (pi >= p.length()) {
            return false;
        }

        boolean ans = false;
        if (p.charAt(pi) == '*') {
            if (si < s.length() && (s.charAt(si) == p.charAt(pi - 1) || p.charAt(pi - 1) == '.')) {
                ans = backTrack(s, si + 1, p, pi) || backTrack(s, si + 1, p, pi + 1);
            }
            ans = ans || backTrack(s, si, p, pi + 1);
        } else {
            if (si < s.length() && (s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '.')) {
                ans = backTrack(s, si + 1, p, pi + 1);
            }

            if (pi < p.length() - 1 && p.charAt(pi + 1) == '*') {
                ans = ans || backTrack(s, si, p, pi + 2);
            }
        }

        return ans;
    }
}
