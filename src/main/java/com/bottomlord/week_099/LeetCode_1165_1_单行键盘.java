package com.bottomlord.week_099;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2021/6/2 8:41
 */
public class LeetCode_1165_1_单行键盘 {
    public int calculateTime(String keyboard, String word) {
        Map<Character, Integer > map = new HashMap<>();
        for (int i = 0; i < keyboard.length(); i++) {
            map.put(keyboard.charAt(i), i);
        }

        int ans = map.get(word.charAt(0));
        for (int i = 0; i < word.length() - 1; i++) {
            ans += Math.abs(map.get(word.charAt(i)) - map.get(word.charAt(i + 1)));
        }

        return ans;
    }
}
