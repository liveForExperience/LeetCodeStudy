package com.bottomlord.week_125;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chen yue
 * @date 2021-12-05 13:25:33
 */
public class LeetCode_1903_2 {
    public String largestOddNumber(String num) {
        Set<Character> set = new HashSet<>();
        set.add('1');
        set.add('3');
        set.add('5');
        set.add('7');
        set.add('9');
        int start = num.length(), end = -1;
        for (int i = 0; i < num.length(); i++) {
            if (num.charAt(i) != '0') {
                start = i;
                break;
            }
        }

        for (int i = num.length() - 1; i >= 0; i--) {
            if (set.contains(num.charAt(i))) {
                end = i;
                break;
            }
        }

        return end >= start ? num.substring(start, end + 1) : "";
    }
}