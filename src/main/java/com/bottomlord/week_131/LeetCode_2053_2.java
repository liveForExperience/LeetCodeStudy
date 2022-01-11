package com.bottomlord.week_131;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chen yue
 * @date 2022-01-11 22:15:19
 */
public class LeetCode_2053_2 {
    public String kthDistinct(String[] arr, int k) {
        Set<String> set = new HashSet<>(), notOnlyOne = new HashSet<>();
        for (String s : arr) {
            if (!set.add(s)) {
                notOnlyOne.add(s);
            }
        }

        int count = 0;
        for (String s : arr) {
            if (notOnlyOne.contains(s)) {
                continue;
            }

            if (k == ++count) {
                return s;
            }
        }

        return "";
    }
}