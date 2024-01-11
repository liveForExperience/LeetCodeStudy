package com.bottomlord.week_235;

/**
 * @author chen yue
 * @date 2024-01-11 08:29:35
 */
public class LeetCode_2645_1_构造有效字符串的最少插入数 {
    public int addMinimum(String word) {
        int j = 0, cnt = 0;
        for (int i = 0; i < word.length();) {
            if ((word.charAt(i) - 'a') == j) {
                i++;
            } else {
                cnt++;
            }

            j = (j + 1) % 3;
        }

        return cnt + (3 - j) % 3;
    }

    public static void main(String[] args) {
        LeetCode_2645_1_构造有效字符串的最少插入数 t = new LeetCode_2645_1_构造有效字符串的最少插入数();
        t.addMinimum("abc");
    }
}
