package com.bottomlord.week_128;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author chen yue
 * @date 2021-12-23 09:04:17
 */
public class LeetCode_1044_1_最长重复子串 {
    private int prime = 31;

    public String longestDupSubstring(String s) {
        int head = 0, tail = s.length();
        String ans = "";
        while (head <= tail) {
            int mid = (head + tail + 1) / 2;
            String str = find(s, mid);
            if (!Objects.equals(str, "")) {
                head = mid + 1;
                ans = str;
            } else {
                tail = mid - 1;
            }
        }

        return ans;
    }

    private String find(String s, int len) {
        Set<Long> set = new HashSet<>();
        long hash = 0, power = 1;
        for (int i = 0; i < len; i++) {
            hash = hash * prime + s.charAt(i);
            power *= prime;
        }
        set.add(hash);

        String ans = "";
        for (int i = len; i < s.length(); i++) {
            hash = hash * prime + s.charAt(i) - power * s.charAt(i - len);

            if (set.contains(hash) &&  s.indexOf(ans = s.substring(i - len + 1, i + 1)) != i) {
                return ans;
            }

            set.add(hash);
        }

        return ans;
    }
}
