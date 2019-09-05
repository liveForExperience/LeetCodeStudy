package com.bottomlord.week_009;

public class LeetCode_859_1_亲密字符串 {
    public boolean buddyStrings(String A, String B) {
        if (A.length() != B.length()) {
            return false;
        }

        int[] bucket = new int[26];
        Character a1 = null, b1 = null, a2 = null, b2 = null;
        int count = 0;
        for (int i = 0; i < A.length(); i++) {
            if (A.charAt(i) != B.charAt(i)) {
                if (count == 0) {
                    count++;
                    a1 = A.charAt(i);
                    b1 = B.charAt(i);
                } else if (count == 1) {
                    count++;
                    a2 = A.charAt(i);
                    b2 = B.charAt(i);
                } else {
                    return false;
                }
            } else {
                bucket[A.charAt(i) - 'a']++;
            }
        }

        if (a1 == null || a2 == null) {
            boolean find = false;
            for (int value : bucket) {
                if (value >= 2) {
                    find = true;
                    break;
                }
            }

            return find;
        }

        return a1 == b2 && a2 == b1;
    }
}
