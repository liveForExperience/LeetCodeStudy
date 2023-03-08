package com.bottomlord.week_191;

/**
 * @author chen yue
 * @date 2023-03-03 08:55:30
 */
public class LeetCode_880_1_索引处的解码字符串 {
    public String decodeAtIndex(String s, int k) {
        long cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                cnt = cnt * (s.charAt(i) - '0');
            } else {
                cnt++;
            }
        }

        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                cnt /= (c - '0');
                k %= cnt;
            } else {
                if (k % cnt == 0) {
                    return s.charAt(i) + "";
                }

                cnt--;
            }
        }

        return "";
    }
}
