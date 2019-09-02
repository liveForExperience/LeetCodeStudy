package com.bottomlord.week_008;

public class LeetCode_443_1_压缩字符串 {
    public int compress(char[] chars) {
        int oldI = 0, newI = 0, pre = 0;
        for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] == chars[i + 1]) {
                oldI++;
                if (i + 1 == chars.length - 1) {
                    int len = ++oldI - pre;
                    String lenS = String.valueOf(len);
                    for (char c : lenS.toCharArray()) {
                        chars[++newI] = c;
                    }
                }
            } else {
                if (oldI == pre) {
                    oldI++;
                } else {
                    int len = ++oldI - pre;
                    String lenS = String.valueOf(len);
                    for (char c : lenS.toCharArray()) {
                        chars[++newI] = c;
                    }
                }
                chars[++newI] = chars[i + 1];
                pre = oldI;
            }
        }

        return newI + 1;
    }
}
