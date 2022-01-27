package com.bottomlord.week_133;

/**
 * @author chen yue
 * @date 2022-01-27 09:05:04
 */
public class LeetCode_2138_1_将字符串拆分为若干长度为k的组 {
    public String[] divideString(String s, int k, char fill) {
        int n = s.length(), left = n % k == 0 ? 0 : k - (n % k);

        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < left; i++) {
            sb.append(fill);
        }

        char[] cs = sb.toString().toCharArray();

        StringBuilder sb2 = new StringBuilder();
        String[] ans = new String[sb.length() / k];
        int index = 0;

        for (int i = 1; i <= cs.length; i++) {
            sb2.append(cs[i - 1]);

            if (sb2.length() % k == 0) {
                ans[index++] = sb2.toString();
                sb2 = new StringBuilder();
            }
        }

        return ans;
    }
}
