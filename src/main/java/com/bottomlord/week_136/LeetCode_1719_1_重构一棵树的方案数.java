package com.bottomlord.week_136;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author chen yue
 * @date 2022-02-16 08:50:37
 */
public class LeetCode_1719_1_重构一棵树的方案数 {
    public int checkWays(int[][] pairs) {
        int root = -1;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] pair : pairs) {
            map.computeIfAbsent(pair[0], x -> new HashSet<>()).add(pair[1]);
            map.computeIfAbsent(pair[1], x -> new HashSet<>()).add(pair[0]);
        }

        for (Map.Entry<Integer, Set<Integer>> entry : map.entrySet()) {
            if (entry.getValue().size() == map.size() - 1) {
                root = entry.getKey();
            }
        }

        if (root == -1) {
            return 0;
        }

        int ans = 1;
        for (Map.Entry<Integer, Set<Integer>> entry : map.entrySet()) {
            int node = entry.getKey();
            if (node == root) {
                continue;
            }

            Set<Integer> degrees = entry.getValue();
            int curDegreeSize = degrees.size();
            int parent = -1;
            int parentDegreeSize = Integer.MAX_VALUE;

            for (Integer degree : degrees) {
                if (map.get(degree).size() < parentDegreeSize && map.get(degree).size() >= curDegreeSize) {
                    parent = degree;
                    parentDegreeSize = map.get(degree).size();
                }
            }

            if (parent == -1) {
                return 0;
            }

            for (Integer degree : degrees) {
                if (degree == parent) {
                    continue;
                }

                if (!map.get(degree).contains(parent)) {
                    return 0;
                }
            }

            if (curDegreeSize == parentDegreeSize) {
                ans = 2;
            }
        }

        return ans;
    }
}
