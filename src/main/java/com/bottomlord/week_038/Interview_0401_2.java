package com.bottomlord.week_038;

import java.util.*;

/**
 * @author ChenYue
 * @date 2020/3/23 8:32
 */
public class Interview_0401_2 {
    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        Map<Integer, List<Integer>> map = new HashMap<>(n);
        for (int[] arr : graph) {
            List<Integer> set = map.getOrDefault(arr[0], new ArrayList<>());
            set.add(arr[1]);
            map.put(arr[0], set);
        }

        boolean[] visited = new boolean[n + 1];
        return dfs(map, visited, start, target);
    }

    private boolean dfs(Map<Integer, List<Integer>> map, boolean[] visited, int start, int target) {
        if (!map.containsKey(start)) {
            return false;
        }

        List<Integer> set = map.get(start);
        if (set.contains(target)) {
            return true;
        }

        visited[start] = true;
        for (int num : set) {
            if (!visited[num] && dfs(map, visited, num, target)) {
                return true;
            }
        }

        return false;
    }
}
