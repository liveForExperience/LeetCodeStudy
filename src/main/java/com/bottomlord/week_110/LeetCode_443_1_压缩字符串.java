package com.bottomlord.week_110;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2021-08-21 10:43:28
 */
public class LeetCode_443_1_压缩字符串 {
    public int compress(char[] chars) {
        int i1 = 0, n = chars.length, count = 0;
        char c = chars[0];
        for (int i = 0; i < n; i++) {
            if (c == chars[i]) {
                count++;
            } else {
                i1++;
                c = chars[i];
                if (count != 1) {
                    char[] ccs = Integer.toString(count).toCharArray();
                    for (char cc : ccs) {
                        chars[i1++] = cc;
                    }
                    count = 1;
                }
                chars[i1] = c;
            }
        }

        i1++;
        if (count == 1) {
            return i1;
        }

        char[] ccs = Integer.toString(count).toCharArray();
        for (char cc : ccs) {
            chars[i1++] = cc;
        }

        return i1;
    }
}
