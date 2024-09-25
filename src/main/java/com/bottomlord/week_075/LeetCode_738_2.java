package com.bottomlord.week_075;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2020/12/15 8:40
 */
public class LeetCode_738_2 {
    public int monotoneIncreasingDigits(int N) {
        if (N < 10) {
            return N;
        }

        String str = Integer.toString(N);
        char[] cs = str.toCharArray();
        int start9Index = str.length();
        for (int i = cs.length - 1; i > 0; i--) {
            if (cs[i] < cs[i - 1]) {
                cs[i - 1] = (char)(cs[i - 1] - 1);
                start9Index = i;
            }
        }

        StringBuilder sb = new StringBuilder();
        int start = 0;
        for (; start < start9Index; start++) {
            if (cs[start] != '0') {
                break;
            }
        }

        for (int i = start; i < start9Index; i++) {
            sb.append(cs[i]);
        }

        if (start9Index < str.length()) {
            char[] cs9 = new char[str.length() - start9Index];
            Arrays.fill(cs9, '9');
            sb.append(cs9);
        }

        return Integer.parseInt(sb.toString());
    }
}
