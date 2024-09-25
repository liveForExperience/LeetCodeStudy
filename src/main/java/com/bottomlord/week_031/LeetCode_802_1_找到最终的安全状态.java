package com.bottomlord.week_031;

import java.util.*;

/**
 * @author ThinkPad
 * @date 2020/2/3 16:08
 */
public class LeetCode_802_1_找到最终的安全状态 {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int len = graph.length;

        boolean[] safe = new boolean[len];
        List<Set<Integer>> graphs = new ArrayList<>();
        List<Set<Integer>> rGraphs = new ArrayList<>();

        for (int i = 0; i < len; i++) {
            graphs.add(new HashSet<>());
            rGraphs.add(new HashSet<>());
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            if (graph[i].length == 0) {
                queue.add(i);
            }
            for (int j : graph[i]) {
                graphs.get(i).add(j);
                rGraphs.get(j).add(i);
            }
        }

        while (!queue.isEmpty()) {
            int num = queue.poll();
            safe[num] = true;

            for (int in : rGraphs.get(num)) {
                graphs.get(in).remove(num);
                if (graphs.get(in).isEmpty()) {
                    queue.offer(in);
                }
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (safe[i]) {
                ans.add(i);
            }
        }

        return ans;
    }
}