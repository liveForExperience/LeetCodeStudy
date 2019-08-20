package com.bottomlord.week_7;

public class LeetCode_459_3 {
    public boolean repeatedSubstringPattern(String s) {
        int len = s.length();
        if (len <= 1) {
            return false;
        }

        char c = s.charAt(len - 1);
        int l = s.lastIndexOf(c, len / 2) + 1;
        for (; l > 0; l = s.lastIndexOf(c, l - 2) + 1) {
            if (len % l == 0) {
                String one = s.substring(0, l);
                boolean flag = true;
                for (int i = l; i < len; i += l) {
                    if (!one.equals(s.substring(i, i + l))) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    return true;
                }
            }
        }

        return false;
    }
}