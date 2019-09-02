package com.bottomlord.week_006;

public class LeetCode_796_2 {
    public boolean rotateString(String A, String B) {
        return A.length() == B.length() && (A + A).contains(B);
    }
}