package com.bottomlord.week_111;

/**
 * @author chen yue
 * @date 2021-08-28 10:50:55
 */
public class LeetCode_1544_1_整理字符串 {
    public String makeGood(String s) {
        while (s.length() > 0) {
            boolean flag = true;
            for (int i = 0; i < s.length() - 1; i++) {
                if (Math.abs(s.charAt(i) - s.charAt(i + 1)) == 32) {
                    s = s.substring(0, i) + s.substring(i + 2);
                    flag = false;
                    break;
                }
            }

            if (flag) {
                break;
            }
        }

        return s;
    }
}
