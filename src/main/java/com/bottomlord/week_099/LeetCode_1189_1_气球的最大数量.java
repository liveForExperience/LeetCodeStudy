package com.bottomlord.week_099;

public class LeetCode_1189_1_气球的最大数量 {
    public int maxNumberOfBalloons(String text) {
        int b = 0, a = 0, l = 0, o = 0, n = 0;
        for (int i = 0; i < text.length(); i++) {
            int c = text.charAt(i);
            if (c == 'b') {
                b++;
            } else if (c == 'a') {
                a++;
            } else if (c == 'l') {
                l++;
            } else if (c == 'o') {
                o++;
            } else if (c == 'n') {
                n++;
            }
        }

        return Math.min(b, Math.min(a, Math.min(l / 2, Math.min(o / 2, n))));
    }
}
