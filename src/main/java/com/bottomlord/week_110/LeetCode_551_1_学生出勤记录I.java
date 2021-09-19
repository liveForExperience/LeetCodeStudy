package com.bottomlord.week_110;

/**
 * @author chen yue
 * @date 2021-08-17 08:13:04
 */
public class LeetCode_551_1_学生出勤记录I {
    public boolean checkRecord(String s) {
        int ac = 0, lc = 0;
        char[] cs = s.toCharArray();

        for (char c : cs) {
            if (c == 'A') {
                ac++;
                lc = 0;
            } else if (c == 'L') {
                lc++;
            } else {
                lc = 0;
            }

            if (ac >= 2 || lc >= 3) {
                return false;
            }
        }

        return true;
    }
}
