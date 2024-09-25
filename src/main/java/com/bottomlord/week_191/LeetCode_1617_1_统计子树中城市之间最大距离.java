package com.bottomlord.week_191;

import java.util.*;

/**
 * @author chen yue
 * @date 2023-03-12 15:04:16
 */
public class LeetCode_1617_1_统计子树中城市之间最大距离 {
    private Map<Integer, List<Integer>> map;
    private int[] ans;
    private boolean[] choose, visited;
    private int n;
    public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
        this.n = n;
        this.map = new HashMap<>();
        for (int[] edge : edges) {
            int x = edge[0] - 1, y = edge[1] - 1;
            map.computeIfAbsent(x, item -> new ArrayList<>()).add(y);
            map.computeIfAbsent(y, item -> new ArrayList<>()).add(x);
        }

        ans = new int[n - 1];
        choose = new boolean[n];
        backTrack(0);
        return ans;
    }

    private void backTrack(int cur) {
        if (visited[cur]) {
            return;
        }

        if (cur == n) {
            visited = new boolean[n];
            int[] dp1 = new int[n], dp2 = new int[n];
            for (int i = 0; i < choose.length; i++) {
                if (!choose[i]) {
                    continue;
                }

                dfs(i, dp1, dp2);
                break;
            }

            int dis = 0;
            for (int i = 0; i < n; i++) {
                dis = Math.max(dis, dp1[i] + dp2[i]);
            }

            if (dis > 0 && Arrays.equals(choose, visited)) {
                ans[dis - 1]++;
            }
            return;
        }

        backTrack(cur + 1);

        choose[cur] = true;
        backTrack(cur + 1);
        choose[cur] = false;
    }

    private void dfs(int cur, int[] dp1, int[] dp2) {
        visited[cur] = true;
        for (Integer next : map.getOrDefault(cur, new ArrayList<>())) {
            if (visited[next] || !choose[next]) {
                continue;
            }

            dfs(next, dp1, dp2);
            int dis = dp1[next] + 1;
            if (dis >= dp1[cur]) {
                dp2[cur] = dp1[cur];
                dp1[cur] = dis;
            } else if (dis > dp2[cur]){
                dp2[cur] = dis;
            }
        }
    }
}
