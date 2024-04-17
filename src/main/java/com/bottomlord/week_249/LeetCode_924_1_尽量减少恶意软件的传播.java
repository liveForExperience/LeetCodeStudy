package com.bottomlord.week_249;

import com.bottomlord.LeetCodeUtils;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * @author chen yue
 * @date 2024-04-17 17:42:40
 */
public class LeetCode_924_1_尽量减少恶意软件的传播 {
    public int minMalwareSpread(int[][] graph, int[] initial) {
        int n = graph.length;
        int[] memo = new int[n];
        int index = 0;
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (memo[i] != 0) {
                continue;
            }

            index++;
            int cnt = 1;
            memo[i] = index;
            Queue<Integer> queue = new ArrayDeque<>();
            queue.offer(i);
            while (!queue.isEmpty()) {
                int cur = queue.poll();

                for (int next = 0; next < n; next++) {
                    if (memo[next] != 0 || graph[cur][next] != 1) {
                        continue;
                    }

                    cnt++;
                    memo[next] = index;
                    queue.offer(next);
                }
            }

            indexMap.put(index, cnt);
        }

        Map<Integer, Integer> initialMap = new HashMap<>();
        for (int i : initial) {
            initialMap.put(memo[i], initialMap.getOrDefault(memo[i], 0) + 1);
        }

        int ans = n, max = 0;
        for (int i : initial) {
            int removed = initialMap.get(memo[i]) == 1 ? indexMap.get(memo[i]) : 0;
            if (removed > max || (removed == max && i < ans)) {
                max = removed;
                ans = i;
            }
        }

        return ans;
    }
}
