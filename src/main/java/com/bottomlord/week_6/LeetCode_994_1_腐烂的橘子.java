package com.bottomlord.week_6;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class LeetCode_994_1_腐烂的橘子 {
    int[] dr = new int[]{-1, 1, 0, 0};
    int[] dc = new int[]{0, 0, -1, 1};
    public int orangesRotting(int[][] grid) {
        int ans = 0, gr = grid.length, gc = grid[0].length, x = Math.max(gr, gc);
        Queue<Integer> queue = new ArrayDeque<>();
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < gr; i++) {
            for (int j = 0; j < gc; j++) {
                if (grid[i][j] == 2) {
                    int num = x * i + j;
                    queue.offer(num);
                    map.put(num, 0);
                }
            }
        }

        while (!queue.isEmpty()) {
            int num = queue.poll();
            int r = num / x;
            int c = num % x;

            for (int i = 0; i < 4; i++) {
                int tmpR = r + dr[i];
                int tmpC = c + dc[i];
                if (tmpR >= 0 && tmpR < gr && tmpC >= 0 && tmpC < gc && grid[tmpR][tmpC] == 1) {
                    int tmpNum = tmpR * x + tmpC;
                    grid[tmpR][tmpC] = 2;
                    queue.offer(tmpR * x + tmpC);
                    map.put(tmpNum, map.get(num) + 1);
                    ans = map.get(tmpNum);
                }
            }
        }

        for (int[] arr : grid) {
            for (int num : arr) {
                if (num == 1) {
                    return -1;
                }
            }
        }

        return ans;
    }
}
