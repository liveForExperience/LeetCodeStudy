package com.bottomlord.contest_20191103;

public class Contest_1_交换字符使得字符串相同 {
    public int minimumSwap(String s1, String s2) {
        int x1 = 0,  y1 = 0, count = 0;
        for (int i = 0; i < s1.length(); i++) {
            count++;
            if (s1.charAt(i) != s2.charAt(i)) {
                if (s1.charAt(i) == 'x') {
                    x1++;
                } else {
                    y1++;
                }
            }
        }

        if (count % 2 == 1) {
            return -1;
        }

        return x1 % 2 * 2 + x1 / 2 + y1 / 2;
    }
}
