package com.bottomlord.week_056;

import java.util.Objects;

/**
 * @author ChenYue
 * @date 2020/7/28 11:23
 */
public class LeetCode_161_1_相隔为1的编辑距离 {
    public boolean isOneEditDistance(String s, String t) {
        int sl = s.length(), tl = t.length();
        if (tl > sl) {
            return isOneEditDistance(t, s);
        }

        if (sl - tl > 1) {
            return false;
        }

        for (int i = 0; i < tl; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (sl == tl) {
                    return Objects.equals(s.substring(i + 1), t.substring(i + 1));
                } else {
                    return Objects.equals(s.substring(i + 1), t.substring(i));
                }
            }
        }

        return tl + 1 == sl;
    }
}
