package com.bottomlord.week_099;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author ChenYue
 * @date 2021/6/3 8:54
 */
public class LeetCode_1180_1_统计只含单一字母的子串 {
    public int countLetters(String s) {
        Set<String> set = new HashSet<>();
        StringBuilder sb = new StringBuilder().append(s.charAt(0));
        set.add(sb.toString());
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != s.charAt(i - 1)) {
                sb.setLength(0);
            }
            sb.append(s.charAt(i));
            set.add(sb.toString());
        }

        int ans = 0;
        for (String str : set) {
            int index = -1;
            do {
                index = s.indexOf(str, index + 1);
                if (index != -1) {
                    ans++;
                }
            } while (index != -1);
        }

        return ans;
    }
}
