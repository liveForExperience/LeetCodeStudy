package com.bottomlord.week_065;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author ChenYue
 * @date 2020/9/29 8:38
 */
public class LeetCode_291_1_单词规律II {
    private Map<String, String> map = new HashMap<>();
    public boolean wordPatternMatch(String pattern, String str) {
        if (pattern.length() == 0) {
            return str.length() == 0;
        }

        String p = pattern.substring(0, 1);
        for (int i = 1; i <= str.length() - pattern.length() + 1; i++) {
            String s = str.substring(0, i), mapping = map.get(p);

            boolean isNewMatch = mapping == null && !map.containsValue(s),
                    isOldMatch = mapping != null && Objects.equals(map.get(p), s);
            if (isNewMatch || isOldMatch) {
                map.put(p, s);
                if (wordPatternMatch(pattern.substring(1), str.substring(i))) {
                    return true;
                }
                if (isNewMatch) {
                    map.remove(p);
                }
            }
        }

        return false;
    }
}
