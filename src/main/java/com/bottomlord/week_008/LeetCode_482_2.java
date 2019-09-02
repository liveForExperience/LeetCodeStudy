package com.bottomlord.week_008;

public class LeetCode_482_2 {
    public String licenseKeyFormatting(String S, int K) {
        int len = S.length(), count = 0;
        char[] cs = S.toCharArray();
        char[] ans = new char[len * 2];
        int index = ans.length - 1;
        for (int i = len - 1; i >= 0; i--) {
            char c = cs[i];

            if (c == '-') {
                continue;
            }

            if (count == K) {
                ans[index--] = '-';
                count = 0;
            }

            if (c >= 'a' && c <= 'z') {
                c -= ' ';
            }

            ans[index--] = c;
            count++;
        }

        return new String(ans, ++index, ans.length - index);
    }
}