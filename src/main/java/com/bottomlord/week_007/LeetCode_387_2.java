package com.bottomlord.week_007;

public class LeetCode_387_2 {
    public int firstUniqChar(String s) {
        int index = -1;
        for (char i = 'a'; i <= 'z'; i++) {
            int start = s.indexOf(i);
            if (start != -1 && s.indexOf(i) == s.lastIndexOf(i)) {
                index = (index == -1 || index > start) ? start : index;
            }
        }

        return index == s.length() ? -1 : index;
    }
}