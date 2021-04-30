package com.bottomlord.week_094;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2021/4/30 14:47
 */
public class LeetCode_555_2 {
    public String splitLoopedString(String[] strs) {
        String[] revs = new String[strs.length];
        String[] origin = Arrays.copyOfRange(strs, 0, strs.length);

        for (int i = 0; i < strs.length; i++) {
            String str = strs[i], rev = reverse(str);
            strs[i] = max(str, rev);
            revs[i] = rev;
        }

        String ans = String.join("", strs);
        for (int i = 0; i < strs.length; i++) {
            String str = origin[i], rev = revs[i];
            int index = 1;
            StringBuilder sb = new StringBuilder();
            while (index < strs.length) {
                sb.append(strs[(i + index) % strs.length]);
                index++;
            }
            String other = sb.toString();

            for (int j = 0 ; j < str.length(); j++) {
                String cur = str.substring(j) + other + str.substring(0, j);
                ans = max(ans, cur);
            }

            for (int j = 0 ; j < rev.length(); j++) {
                String cur = rev.substring(j) + other + rev.substring(0, j);
                ans = max(ans, cur);
            }
        }

        return ans;
    }

    private String reverse(String str) {
        char[] cs = str.toCharArray();
        int len = cs.length;
        for (int i = 0; i < len / 2; i++) {
            char tmp = cs[i];
            cs[i] = cs[len - i - 1];
            cs[len - i - 1] = tmp;
        }
        return new String(cs);
    }

    private String max(String a, String b) {
        if (a == null) {
            return b;
        }

        if (b == null) {
            return a;
        }

        return a.compareTo(b) > 0 ? a : b;
    }
}
