package com.bottomlord.week_065;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2020/9/30 8:45
 */
public class LeetCode_294_1_翻转游戏II {
    private Map<String, Boolean> map = new HashMap<>();
    public boolean canWin(String s) {
        if (map.containsKey(s)) {
            return map.get(s);
        }

        char[] cs = s.toCharArray();
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i - 1) == '+' && s.charAt(i) == '+') {
                cs[i - 1] = '-';
                cs[i] = '-';
                String str = new String(cs);
                if (!canWin(str)) {
                    return true;
                }
                map.put(str, true);
                cs[i - 1] = '+';
                cs[i] = '+';
            }
        }

        return false;
    }
}
