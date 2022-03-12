package com.bottomlord.week_139;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2022-03-11 22:32:39
 */
public class LeetCode_2049_1_统计最高分的节点数目 {
    public int countHighestScoreNodes(int[] parents) {
        int n = parents.length;
        List<Integer>[] graph = new ArrayList[n];

        for (int i = 1; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < parents.length; i++) {
            graph[parents[i]].add(i);
        }

        long max = 0;
        int ans = 0;
        Integer[] memo = new Integer[n];
        for (int i = 0; i < n; i++) {
            long count = dfs(graph, i, n, memo);
            if (count == max)  {
                ans++;
            } else if (count > max) {
                ans = 1;
                max = count;
            }
        }

        return ans;
    }

    private long dfs(List<Integer>[] graph, int node, int n, Integer[] memo) {
        List<Integer> children = graph[node];

        long ans = 1, count = 0;
        for (Integer child : children) {
            Integer m = memo[child];
            int childCount;
            if (m == null) {
               childCount = innerDfs(graph, child, memo);
               memo[child] = childCount;
            } else {
                childCount = m;
            }

            ans *= childCount;
            count += childCount;
        }

        if (node != 0) {
            ans *= (n - 1 - count);
        }

        return ans;
    }

    private int innerDfs(List<Integer>[] graph, int node, Integer[] memo) {
        int count = 1;

        List<Integer> children = graph[node];
        for (Integer child : children) {
            Integer m = memo[child];
            int childCount;
            if (m == null) {
                childCount = innerDfs(graph, child, memo);
                memo[child] = childCount;
            } else {
                childCount = m;
            }

            count += childCount;
        }

        return count;
    }
}
