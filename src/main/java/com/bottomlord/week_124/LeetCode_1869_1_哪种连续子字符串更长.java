package com.bottomlord.week_124;

/**
 * @author chen yue
 * @date 2021-11-23 08:56:17
 */
public class LeetCode_1869_1_哪种连续子字符串更长 {
    public boolean checkZeroOnes(String s) {
        int len = s.length(), max1 = 0, max0 = 0;
        for (int i = 0; i < len;) {
            if (s.charAt(i) == '1') {
                int one = 0;
                while (i < len && s.charAt(i) == '1') {
                    one++;
                    i++;
                }

                max1 = Math.max(one, max1);
            } else {
                int zero = 0;
                while (i < len && s.charAt(i) == '0') {
                    zero++;
                    i++;
                }

                max0 = Math.max(zero, max0);
            }
        }

        return max1 > max0;
    }
}
