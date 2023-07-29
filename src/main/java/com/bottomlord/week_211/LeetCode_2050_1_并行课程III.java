package com.bottomlord.week_211;

import java.util.*;

/**
 * @author chen yue
 * @date 2023-07-28 23:49:26
 */
public class LeetCode_2050_1_并行课程III {
    public int minimumTime(int n, int[][] relations, int[] time) {
        int[] dp = new int[n + 1], inDegrees = new int[n + 1];
        int ans = 0;
        Map<Integer, List<Integer>> edge = new HashMap<>();
        Queue<Integer> queue = new ArrayDeque<>();
        for (int[] relation : relations) {
            inDegrees[relation[1]]++;
            edge.computeIfAbsent(relation[0], x -> new ArrayList<>()).add(relation[1]);
        }

        for (int i = 1; i < inDegrees.length; i++) {
            if (inDegrees[i] == 0) {
                queue.offer(i);
                dp[i] = time[i - 1];
                ans = Math.max(dp[i], ans);
            }
        }

        while (!queue.isEmpty()) {
            int i = queue.poll();
            if (!edge.containsKey(i)) {
                continue;
            }

            for (Integer j : edge.get(i)) {
                dp[j] = Math.max(dp[j], dp[i] + time[j - 1]);
                ans = Math.max(dp[j], ans);
                if (--inDegrees[j] == 0) {
                    queue.offer(j);
                }
            }
        }

        return ans;
    }
}
