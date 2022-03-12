package com.bottomlord.week_139;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2022-03-12 19:10:23
 */
public class LeetCode_2049_2 {
    private int ans = 0, n;
    private long max = 0;
    public int countHighestScoreNodes(int[] parents) {
        n = parents.length;
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            graph[parents[i]].add(i);
        }

        dfs(graph, 0);

        return ans;
    }

    private int dfs(List<Integer>[] graph, int node) {
        List<Integer> children = graph[node];
        long cur = 1;
        int count = 0;
        for (Integer child : children) {
            int childCount = dfs(graph, child);
            cur *= childCount;
            count += childCount;
        }

        if (node != 0) {
            cur *= (n - count - 1);
        }

        if (max == cur) {
            ans++;
        } else if (max < cur) {
            max = cur;
            ans = 1;
        }

        return count + 1;
    }
}