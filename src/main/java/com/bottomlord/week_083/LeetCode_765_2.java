package com.bottomlord.week_083;

import java.util.*;

/**
 * @author ChenYue
 * @date 2021/2/15 16:58
 */
public class LeetCode_765_2 {
    public int minSwapsCouples(int[] row) {
        int len = row.length, total = len / 2;
        List<Integer>[] graph = new ArrayList[total];
        for (int i = 0; i < total; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < len; i += 2) {
            int l = row[i] / 2, r = row[i + 1] / 2;
            if (l == r) {
                continue;
            }
            graph[l].add(r);
            graph[r].add(l);
        }

        int ans = 0;
        boolean[] memo = new boolean[total];

        for (int i = 0; i < total; i++) {
            if (memo[i]) {
                continue;
            }
            int count = 0;
            Queue<Integer> queue = new ArrayDeque<>();
            memo[i] = true;
            queue.offer(i);
            while (!queue.isEmpty()) {
                Integer num = queue.poll();
                count++;
                for (Integer g : graph[num]) {
                    if (!memo[g]) {
                        memo[g] = true;
                        queue.offer(g);
                    }
                }
            }

            ans += count - 1;
        }

        return ans;
    }
}
