package com.bottomlord.week_216;

/**
 * @author chen yue
 * @date 2023-09-03 11:25:03
 */
public class LeetCode_1921_2 {
    public int eliminateMaximum(int[] dist, int[] speed) {
        int n = dist.length, kill = 0;

        int[] arr = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int round = (dist[i] - 1) / speed[i] + 1;
            if (round > n) {
                continue;
            }

            arr[round]++;
        }

        for (int i = 0; i <= n; i++) {
            kill -= arr[i];
            if (kill < 0) {
                return i;
            }
            kill++;
        }

        return n;
    }
}
