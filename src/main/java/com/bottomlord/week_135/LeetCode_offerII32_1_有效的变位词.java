package com.bottomlord.week_135;

import java.util.Objects;

/**
 * @author chen yue
 * @date 2022-02-13 15:58:53
 */
public class LeetCode_offerII32_1_有效的变位词 {
    public boolean isAnagram(String s, String t) {
        if (Objects.equals(s, t)) {
            return false;
        }

        int[] arr = new int[26];
        for (char c : s.toCharArray()) {
            arr[c - 'a']++;
        }

        for (char c : t.toCharArray()) {
            arr[c - 'a']--;
        }

        for (int num : arr) {
            if (num != 0) {
                return false;
            }
        }

        return true;
    }
}
