package com.bottomlord.week_242;

import java.util.*;

/**
 * @author chen yue
 * @date 2024-03-02 19:21:12
 */
public class LeetCode_2368_1_受限条件下可到达节点的数目 {
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        boolean[] memo = new boolean[n];
        for (int i : restricted) {
            memo[i] = true;
        }

        int ans = 0;
        List<Integer>[] matrix = new List[n];
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            matrix[edge[0]].add(edge[1]);
            matrix[edge[1]].add(edge[0]);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(0);
        while (!queue.isEmpty()) {
            int cnt = queue.size();
            while (cnt-- > 0) {
                Integer index = queue.poll();
                if (index == null) {
                    continue;
                }

                ans++;
                memo[index] = true;

                List<Integer> nexts = matrix[index];
                for (Integer next : nexts) {
                    if (memo[next]) {
                        continue;
                    }

                    queue.offer(next);
                }
            }
        }

        return ans;
    }
}
