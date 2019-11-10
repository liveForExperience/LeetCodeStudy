package com.bottomlord.week_018;

public class LeetCode_1006_3 {
    public boolean isValid(String S) {
        char[] cs = S.toCharArray(), stack = new char[S.length()];
        int index = 0;

        for (char c : cs) {
            if (c == 'c') {
                stack[index++] = c;
            } else {
                if (index < 2) {
                    return false;
                } else {
                    if (stack[--index] != 'b') {
                        return false;
                    }

                    if (stack[--index] != 'a') {
                        return false;
                    }
                }
            }
        }

        return index == 0;
    }
}