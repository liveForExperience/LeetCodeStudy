package com.bottomlord.week_022;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_1023_1_驼峰式匹配 {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> ans = new ArrayList<>(queries.length);
        for (String query : queries) {
            ans.add(match(query, pattern));
        }
        return ans;
    }

    private boolean match(String query, String pattern) {
        int index = 0;
        for (char c : query.toCharArray()) {
            if (index < pattern.length()) {
                if (c == pattern.charAt(index)) {
                    index++;
                } else if (c < 'a' || c > 'z') {
                    return false;
                }
            } else if (c < 'a' || c > 'z') {
                return false;
            }
        }

        return index >= pattern.length();
    }
}
