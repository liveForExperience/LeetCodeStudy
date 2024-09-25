package com.bottomlord.week_016;

public class LeetCode_835_1_图像重叠 {
    public int largestOverlap(int[][] A, int[][] B) {
        int len = A.length;
        int[][] count = new int[2 * len + 1][2 * len + 1];

        for (int ra = 0; ra < len; ra++) {
            for (int ca = 0; ca < len; ca++) {
                if (A[ra][ca] == 1) {
                    for (int rb = 0; rb < len; rb++) {
                        for (int cb = 0; cb < len; cb++) {
                            if (B[rb][cb] == 1) {
                                count[ra - rb + len][ca - cb + len]++;
                            }
                        }
                    }
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < count.length; i++) {
            for (int j = 0; j < count.length; j++) {
                ans = Math.max(count[i][j], ans);
            }
        }
        return ans;
    }
}
