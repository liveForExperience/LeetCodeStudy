package com.bottomlord.week_033;

/**
 * @author ThinkPad
 * @date 2020/2/20 12:39
 */
public class Interview_19_2 {
    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) {
            return s.isEmpty();
        }

        boolean firstMatch = s.charAt(0) == p.charAt(0) || p.charAt(0) == '.';

        if (p.length() >= 2 && p.charAt(1) == '*') {
            return isMatch(s, p.substring(2)) || (firstMatch && isMatch(s.substring(1), p));
        }
        return firstMatch && isMatch(s.substring(1), p.substring(1));
    }
}