package com.bottomlord.week_105;

/**
 * @author ChenYue
 * @date 2021/7/16 8:41
 */
public class LeetCode_1309_2 {
    public String freqAlphabets(String s) {
        StringBuilder sb = new StringBuilder();
        char[] cs = s.toCharArray();
        for (int i = cs.length - 1; i >= 0; i--) {
            if (cs[i] == '#') {
                sb.insert(0, (char) (cs[--i] - 48 + (cs[--i] - 48) * 10 + 96));
            } else {
                sb.insert(0, (char) (cs[i] + 48));
            }
        }

        return sb.toString();
    }
}
