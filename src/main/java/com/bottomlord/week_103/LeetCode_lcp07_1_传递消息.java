package com.bottomlord.week_103;

import java.util.*;

/**
 * @author ChenYue
 * @date 2021/7/1 8:10
 */
public class LeetCode_lcp07_1_传递消息 {
    public int numWays(int n, int[][] relation, int k) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] arr : relation) {
            List<Integer> list = map.getOrDefault(arr[0], new ArrayList<>());
            list.add(arr[1]);
            map.put(arr[0], list);
        }

        return dfs(map, 0, n - 1, 0, k);
    }

    private int dfs(Map<Integer, List<Integer>> map, int cur, int target, int round, int targetRound) {
        if (round > targetRound) {
            return 0;
        }

        if (round == targetRound) {
            return cur == target ? 1 : 0;
        }

        int count = 0;
        List<Integer> list = map.getOrDefault(cur, new ArrayList<>());
        for (int num : list) {
            count += dfs(map, num, target, round + 1, targetRound);
        }

        return count;
    }
}
