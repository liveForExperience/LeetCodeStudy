package com.bottomlord.contest_20220417;

/**
 * @author chen yue
 * @date 2022-04-17 19:38:04
 */
public class Contest_1_2 {
    public String digitSum(String s, int k) {
        if (s.length() <= k) {
            return s;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length();) {
            int index = 0;
            int num = 0;
            while (index < k && i < s.length()) {
                num += s.charAt(i) - '0';
                index++;
                i++;
            }

            sb.append(num);
        }

        return digitSum(sb.toString(), k);
    }
}
