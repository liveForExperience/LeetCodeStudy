package com.bottomlord.week_181;

/**
 * @author chen yue
 * @date 2022-12-27 23:22:33
 */
public class LeetCode_2027_1_转换字符串的最少操作次数 {
    public int minimumMoves(String s) {
        char[] cs = s.toCharArray();
        int count = 0;
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == 'X') {
                cs[i] = 'O';

                if (i + 1 < cs.length) {
                    cs[i + 1] = 'O';
                }

                if (i + 2 < cs.length) {
                    cs[i + 2] = 'O';
                }

                i += 2;
                count++;
            }
        }

        return count;
    }
}
