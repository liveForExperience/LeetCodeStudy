package com.bottomlord.week_127;

import java.util.*;

/**
 * @author chen yue
 * @date 2021-12-15 09:04:32
 */
public class LeetCode_851_1_喧闹与富有 {
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int n = quiet.length;
        int[][] w = new int[n][n];
        int[] in = new int[n];

        for (int[] rich : richer) {
            w[rich[0]][rich[1]] = 1;
            in[rich[1]]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        int[] ans = new int[n];
        for (int i = 0; i < in.length; i++) {
            ans[i] = i;
            if (in[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int rich = queue.poll();
            for (int i = 0; i < n; i++) {
                if (w[rich][i] == 1) {
                    if (quiet[ans[rich]] < quiet[ans[i]]) {
                        ans[i] = ans[rich];
                    }

                    if (--in[i] == 0) {
                        queue.offer(i);
                    }
                }
            }
        }

        return ans;
    }
}
