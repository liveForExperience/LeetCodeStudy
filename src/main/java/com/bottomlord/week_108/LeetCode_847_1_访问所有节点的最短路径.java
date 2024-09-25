package com.bottomlord.week_108;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author ChenYue
 * @date 2021/8/6 8:20
 */
public class LeetCode_847_1_访问所有节点的最短路径 {
    public int shortestPathLength(int[][] graph) {
        int n = graph.length, done = (2 << n) - 1, ans = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] memo = new boolean[n][2];
        for (int i = 0; i < n; i++) {
            queue.offer(new int[]{i, 2 << i, 0});
            memo[i][2 << i] = true;
        }

        while (!queue.isEmpty()) {
            int[] tuple = queue.poll();
            int x = tuple[0], mask = tuple[1], dist = tuple[2];

            if (mask == done) {
                ans = dist;
                break;
            }

            int[] next = graph[x];
            for (int y : next) {
                mask |= 2 << y;
                if (!memo[y][mask]) {
                    queue.offer(new int[]{y, mask, dist + 1});
                    memo[y][mask] = true;
                }
            }
        }

        return ans;
    }
}
