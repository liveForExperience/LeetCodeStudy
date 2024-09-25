package com.bottomlord.week_135;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2022-02-13 16:05:15
 */
public class LeetCode_offerII34_1_外星语言是否排序 {
    public boolean isAlienSorted(String[] words, String order) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            char c = order.charAt(i);
            map.put(c, i);
        }

        map.put(' ', -1);

        int len = words.length;
        for (int i = 0; i < len - 1; i++) {
            char[] cs = get(words[i], words[i + 1]);
            if (cs == null) {
                continue;
            }

            if (map.get(cs[0]) > map.get(cs[1])) {
                return false;
            }
        }
        return true;
    }

    private char[] get(String x, String y) {
        int xl = x.length(), yl = y.length();
        for (int i = 0; i < xl || i < yl; i++) {
            if (i >= xl) {
                return new char[]{' ', y.charAt(i)};
            }

            if (i >= yl) {
                return new char[]{x.charAt(i), ' '};
            }

            if (x.charAt(i) != y.charAt(i)) {
                return new char[]{x.charAt(i), y.charAt(i)};
            }
        }

        return null;
    }
}

















































