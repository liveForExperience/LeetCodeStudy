package com.bottomlord.week_109;

/**
 * @author chen yue
 * @date 2021-08-14 14:25:22
 */
public class LeetCode_1583_1_统计不开心的朋友 {
    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        int[][] dict = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                dict[i][preferences[i][j]] = n - 1 - j;
            }
        }

        int ans = 0;
        for (int i = 0; i < pairs.length; i++) {
            boolean flag1 = false, flag2 = false;

            for (int j = 0; j < pairs.length; j++) {
                if (i == j) {
                    continue;
                }

                int[] pairA = pairs[i], pairB = pairs[j];

                int a12 = dict[pairA[0]][pairA[1]],
                    ab11 = dict[pairA[0]][pairB[0]],
                    ab12 = dict[pairA[0]][pairB[1]],
                    a21 = dict[pairA[1]][pairA[0]],
                    ab21 = dict[pairA[1]][pairB[0]],
                    ab22 = dict[pairA[1]][pairB[1]],
                    b12 = dict[pairB[0]][pairB[1]],
                    ba11 = dict[pairB[0]][pairA[0]],
                    ba12 = dict[pairB[0]][pairA[1]],
                    b21 = dict[pairB[1]][pairB[0]],
                    ba21 = dict[pairB[1]][pairA[0]],
                    ba22 = dict[pairB[1]][pairA[1]];

                if (!flag1 && a12 < ab11 && b12 < ba11) {
                    flag1 = true;
                    ans++;
                }

                if (!flag1 && a12 < ab12 && b21 < ba21) {
                    flag1 = true;
                    ans++;
                }

                if (!flag2 && a21 < ab21 && b12 < ba12) {
                    flag2 = true;
                    ans++;
                }

                if (!flag2 && a21 < ab22 && b21 < ba22) {
                    flag2 = true;
                    ans++;
                }

                if (flag1 && flag2) {
                    break;
                }
            }
        }

        return ans;
    }
}
