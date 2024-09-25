package com.bottomlord.week_038;

import java.util.*;

/**
 * @author ChenYue
 * @date 2020/3/23 8:22
 */
public class Interview_0401_1_节点间通路 {
    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        Map<Integer, Set<Integer>> map = new HashMap<>(n);
        for (int[] arr : graph) {
            Set<Integer> set = map.getOrDefault(arr[0], new HashSet<>());
            set.add(arr[1]);
            map.put(arr[0], set);
        }

        return dfs(map, start, target);
    }

    private boolean dfs(Map<Integer, Set<Integer>> map, int start, int target) {
        if (!map.containsKey(start)) {
            return false;
        }

        boolean ans = false;
        for (int num : map.get(start)) {
            if (num == target) {
                return true;
            }

            ans |= dfs(map, num, target);
        }

        return ans;
    }
}
