package com.bottomlord.week_131;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * @author chen yue
 * @date 2022-01-11 08:51:53
 */
public class LeetCode_1036_1_逃离大迷宫 {
    private long base = 131;
    private int[][] dirs = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    private int max;
    private Set<Long> set = new HashSet<>();
    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        for (int[] block : blocked) {
            set.add(block[0] * base + block[1]);
        }
        int n = blocked.length;
        max = n * (n - 1) / 2;
        return dfs(source, target) && dfs(target, source);
    }

    private boolean dfs(int[] a, int[] b) {
        Set<Long> memo = new HashSet<>();
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(a);
        memo.add(a[0] * base + a[1]);
        while (!queue.isEmpty() && memo.size() <= max) {
            int[] arr = queue.poll();
            int x = arr[0], y = arr[1];
            if (x == b[0] && y == b[1]) {
                return true;
            }

            for (int[] dir : dirs) {
                int nx = dir[0] + arr[0], ny = dir[1] + arr[1];
                if (nx < 0 || nx >= 1000000 || ny < 0 || ny >= 1000000) {
                    continue;
                }

                long hash = nx * base + ny;
                if (set.contains(hash) || memo.contains(hash)) {
                    continue;
                }

                memo.add(hash);
                queue.add(new int[]{nx, ny});
            }
        }

        return memo.size() > max;
    }
}
