package com.bottomlord.week_117;

import java.util.Locale;

/**
 * @author chen yue
 * @date 2021-10-04 17:37:39
 */
public class LeetCode_482_1_秘钥格式化 {
    public String licenseKeyFormatting(String s, int k) {
        StringBuilder sb = new StringBuilder();
        s = s.toUpperCase(Locale.ROOT);
        char[] cs = s.toCharArray();
        for (char c : cs) {
            if (c != '-') {
                sb.append(c);
            }
        }
        cs = sb.toString().toCharArray();

        int n = sb.length();
        boolean flag = n % k == 0;

        int block = flag ? n / k : n / k + 1, first = flag ? k : n % k;

        StringBuilder ans = new StringBuilder();
        int index = 0;
        for (int i = 0; i < block; i++) {
            int loop = i == 0 ? first : k;
            for (int i1 = 0; i1 < loop; i1++) {
                ans.append(cs[index++]);
            }

            if (i != block - 1) {
                ans.append("-");
            }
        }

        return ans.toString();
    }
}
