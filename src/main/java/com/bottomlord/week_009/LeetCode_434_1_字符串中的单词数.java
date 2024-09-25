package com.bottomlord.week_009;

public class LeetCode_434_1_字符串中的单词数 {
    public int countSegments(String s) {
        s = s.trim();
        if ("".equals(s)) {
            return 0;
        }
        return s.split("\\s+").length;
    }
}
