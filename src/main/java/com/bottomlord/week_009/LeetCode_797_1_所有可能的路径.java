package com.bottomlord.week_009;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_797_1_所有可能的路径 {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> ans = new ArrayList<>();
        boolean[] visit = new boolean[graph.length];
        dfs(ans, graph, 0, new ArrayList<>(), visit);
        return ans;
    }

    private void dfs(List<List<Integer>> ans, int[][] graph, int pos, List<Integer> path, boolean[] visit) {
        path.add(pos);
        visit[pos] = true;

        if (pos == graph.length - 1) {
            ans.add(new ArrayList<>(path));
        } else {
            for (int num : graph[pos]) {
                if (!visit[num]) {
                    dfs(ans, graph, num, path, visit);
                }
            }
        }

        path.remove(path.size() - 1);
        visit[pos] = false;
    }
}
