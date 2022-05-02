package com.bottomlord.contest_20220501;

/**
 * @author chen yue
 * @date 2022-05-01 20:25:43
 */
public class Contest_1_2 {
    public String removeDigit(String number, char digit) {
        int len = number.length(), lastIndex = len - 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            char c = number.charAt(i);
            sb.append(c);
            if (c != digit) {
                continue;
            }

            if (i == len - 1) {
                lastIndex = i;
                continue;
            }

            if (c < number.charAt(i + 1)) {
                sb.deleteCharAt(sb.length() - 1);
                sb.append(number.substring(i + 1));
                return sb.toString();
            }

            lastIndex = i;
        }
        sb.deleteCharAt(lastIndex);
        return sb.toString();
    }
}