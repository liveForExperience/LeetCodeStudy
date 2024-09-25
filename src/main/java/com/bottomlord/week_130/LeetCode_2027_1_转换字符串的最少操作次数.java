package com.bottomlord.week_130;

/**
 * @author chen yue
 * @date 2022-01-07 09:09:55
 */
public class LeetCode_2027_1_转换字符串的最少操作次数 {
    public int minimumMoves(String s) {
        char[] cs = s.toCharArray();
        int count = 0, n = s.length();
        for (int i = 0; i < cs.length - 2; i++) {
            if (cs[i] == 'X') {
                count++;
                cs[i] = cs[i + 1] = cs[i + 2] = 'O';
            }
        }

        if (cs[n - 2] == 'X' || cs[n - 1] == 'X') {
            count++;
        }

        return count;
    }
}
