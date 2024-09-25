package com.bottomlord.week_201;

/**
 * @author chen yue
 * @date 2023-05-18 14:18:45
 */
public class LeetCode_984_1_不含aaa或bbb的字符串 {
    public String strWithout3a3b(int a, int b) {
        char maxC, minC;
        int max, min;
        if (a >= b) {
            maxC = 'a';
            minC = 'b';
            max = a;
            min = b;
        } else {
            maxC = 'b';
            minC = 'a';
            max = b;
            min = a;
        }

        int diff = max - min;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < min; i++) {
            sb.append(maxC);
            max--;
            if (diff-- > 0) {
                sb.append(maxC);
                max--;
            }

            sb.append(minC);
        }

        for (int i = 0; i < max; i++) {
            sb.append(maxC);
        }

        return sb.toString();
    }
}
