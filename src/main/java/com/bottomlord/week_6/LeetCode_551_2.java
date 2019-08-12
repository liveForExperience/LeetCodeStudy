package com.bottomlord.week_6;

public class LeetCode_551_2 {
    public boolean checkRecord(String s) {
        if (s.length() - s.replaceAll("A", "").length() > 1) {
            return false;
        }

        return s.length() == s.replaceAll("LLL", "").length();
    }
}