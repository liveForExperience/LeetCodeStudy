package com.bottomlord.week_170;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chen yue
 * @date 2022-10-16 13:15:31
 */
public class LeetCode_886_2 {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        Map<Integer, List<Integer>> g = new HashMap<>();
        for (int[] dislike : dislikes) {
            g.computeIfAbsent(dislike[0], x -> new ArrayList<>()).add(dislike[1]);
            g.computeIfAbsent(dislike[1], x -> new ArrayList<>()).add(dislike[0]);
        }

        UnionFind unionFind = new UnionFind(n);

        for (Map.Entry<Integer, List<Integer>> entry : g.entrySet()) {
            Integer key = entry.getKey();
            List<Integer> list = entry.getValue();

            for (Integer value : list) {
                if (unionFind.find(key) == unionFind.find(value)) {
                    return false;
                }

                unionFind.union(value, list.get(0));
            }
        }

        return true;
    }

    private class UnionFind {
        private int[] parent;

        public UnionFind(int n) {
            this.parent = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                this.parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }

            return parent[x];
        }

        public void union(int x, int y) {
            parent[x] = find(parent[y]);
        }
    }
}