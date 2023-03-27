package com.bottomlord.week_193;

/**
 * @author chen yue
 * @date 2023-03-26 14:26:53
 */
public class LeetCode_957_1_N天后的牢房 {
    public int[] prisonAfterNDays(int[] cells, int n) {
        int[] pre = cells;
        while (n-- > 0) {
            int[] cur = new int[8];
            for (int i = 0; i < 8; i++) {
                if (i == 0 || i == 7) {
                    cur[i] = 0;
                    continue;
                }

                if (pre[i - 1] != pre[i + 1]) {
                    cur[i] = 0;
                } else {
                    cur[i] = 1;
                }
            }

            pre = cur;
        }

        return pre;
    }
}
