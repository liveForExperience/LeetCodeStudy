package com.bottomlord.week_6;


public class LeetCode_844_1_比较含退格的字符串 {
    public boolean backspaceCompare(String S, String T) {
        return generate(S).equals(generate(T));
    }

    private String generate(String str) {
        int skip = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            if (skip > 0) {
                skip--;
                continue;
            }

            if (str.charAt(i) == '#') {
                skip++;
                continue;
            }

            sb.insert(0, str.charAt(i));
        }

        return sb.toString();
    }
}
