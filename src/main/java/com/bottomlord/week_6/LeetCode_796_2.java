package com.bottomlord.week_6;

public class LeetCode_796_2 {
    public boolean rotateString(String A, String B) {
        return A.length() == B.length() && (A + A).contains(B);
    }
}