package com.bottomlord.week_121;

/**
 * @author chen yue
 * @date 2021-11-07 21:28:06
 */
public class LeetCode_598_1_范围求和II {
    public int maxCount(int m, int n, int[][] ops) {
        int r = m, c = n;
        for (int[] op : ops) {
            r = Math.min(r, op[0]);
            c = Math.min(c, op[1]);
        }

        return r * c;
    }
}
