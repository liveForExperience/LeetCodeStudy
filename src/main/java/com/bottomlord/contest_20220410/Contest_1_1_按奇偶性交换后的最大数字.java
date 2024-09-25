package com.bottomlord.contest_20220410;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2022-04-10 10:30:15
 */
public class Contest_1_1_按奇偶性交换后的最大数字 {
    public int largestInteger(int num) {
        String str = Integer.toString(num);
        int len = str.length();
        char[] cs = str.toCharArray();
        boolean[] bs = new boolean[len];

        int oddCount = 0, evenCount = 0;
        for (int i = 0; i < cs.length; i++) {
            int n = cs[i] - '0';

            if (n % 2 == 0) {
                evenCount++;
                bs[i] = false;
            } else {
                oddCount++;
                bs[i] = true;
            }
        }

        int[] odds = new int[oddCount], evens = new int[evenCount];
        int oi = 0, ei = 0;
        for (int i = 0; i < bs.length; i++) {
            if (bs[i]) {
                odds[oi++] = cs[i] - '0';
            } else {
                evens[ei++] = cs[i] - '0';
            }
        }

        Arrays.sort(odds);
        Arrays.sort(evens);

        oi = oddCount - 1;
        ei = evenCount - 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bs.length; i++) {
            if (bs[i]) {
                sb.append(odds[oi--]);
            } else {
                sb.append(evens[ei--]);
            }
        }

        return Integer.parseInt(sb.toString());
    }
}
