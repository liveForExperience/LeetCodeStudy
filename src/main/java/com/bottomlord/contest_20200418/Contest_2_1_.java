package com.bottomlord.contest_20200418;

import java.util.*;

/**
 * @author ChenYue
 * @date 2020/4/18 15:08
 */
public class Contest_2_1_ {
    public int numWays(int n, int[][] relation, int k) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] arr : relation) {
            Set<Integer> set = map.getOrDefault(arr[0], new HashSet<>());
            set.add(arr[1]);
            map.put(arr[0], set);
        }

        return dfs(map, 0, n - 1, k);
    }

    private int dfs(Map<Integer, Set<Integer>> map, int num, int target, int k) {
        if (num == target && k == 0) {
            return 1;
        }

        if (k <= 0) {
            return 0;
        }

        if (!map.containsKey(num)) {
            return 0;
        }

        int ans = 0;
        for (int x : map.get(num)) {
            ans += dfs(map, x, target, k - 1);
        }
        return ans;
    }
}
