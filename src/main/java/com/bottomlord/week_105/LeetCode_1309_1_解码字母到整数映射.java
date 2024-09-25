package com.bottomlord.week_105;

/**
 * @author ChenYue
 * @date 2021/7/16 8:28
 */
public class LeetCode_1309_1_解码字母到整数映射 {
    public String freqAlphabets(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0;) {
            if (s.charAt(i) == '#') {
                int num = Integer.parseInt(String.valueOf(s.charAt(i - 2)) + s.charAt(i - 1));
                sb.insert(0, (char) ('a' + num - 1));
                i -= 3;
            } else {
                int num = Integer.parseInt(String.valueOf(s.charAt(i)));
                sb.insert(0, (char) ('a' + num - 1));
                i--;
            }
        }

        return sb.toString();
    }
}
