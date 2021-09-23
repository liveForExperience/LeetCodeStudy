package com.bottomlord.week_115;

/**
 * @author chen yue
 * @date 2021-09-23 08:50:45
 */
public class LeetCode_1694_1_重新格式化电话号码 {
    public String reformatNumber(String number) {
        StringBuilder sb = new StringBuilder();
        for (char c : number.toCharArray()) {
            if (c == ' ' || c == '-') {
                continue;
            }

            sb.append(c);
        }

        int n = sb.length();
        int left = n % 3, block;

        if (left == 1) {
            block = n / 3 - 1;
            left = 4;
        } else {
            block = n / 3;
        }

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < block; i++) {
            for (int j = 0; j < 3; j++) {
                ans.append(sb.charAt(i * 3 + j));
            }
            ans.append('-');
        }

        if (left == 0) {
            ans.setLength(ans.length() - 1);
            return ans.toString();
        }

        if (left == 2) {
            ans.append(sb.charAt(sb.length() - 2)).append(sb.charAt(sb.length() - 1));
            return ans.toString();
        }

        return ans.append(sb.charAt(sb.length() - 4)).append(sb.charAt(sb.length() - 3))
                .append('-')
                .append(sb.charAt(sb.length() - 2)).append(sb.charAt(sb.length() - 1))
                .toString();
    }
}
