package com.bottomlord.week_009;

public class LeetCode_434_2 {
    public int countSegments(String s) {
        int index = 0, count = 0;
        while (index < s.length()) {
            if (s.charAt(index) != ' ' && (index == 0 || s.charAt(index - 1) == ' ')) {
                count++;
            }
            index++;
        }
        return count;
    }
}