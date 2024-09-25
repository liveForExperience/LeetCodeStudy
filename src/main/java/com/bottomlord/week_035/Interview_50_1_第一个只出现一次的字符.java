package com.bottomlord.week_035;

/**
 * @author ThinkPad
 * @date 2020/3/3 8:40
 */
public class Interview_50_1_第一个只出现一次的字符 {
    public char firstUniqChar(String s) {
        int[] bucket = new int[256];
        char[] cs = new char[256];
        int index = 0;

        for (char c : s.toCharArray()) {
            bucket[c]++;

            if (bucket[c] == 1) {
                cs[index++] = c;
            }
        }

        for (char c : cs) {
            if (bucket[c] == 1) {
                return c;
            }
        }

        return ' ';
    }
}
