package com.bottomlord.week_062;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * @author ChenYue
 * @date 2020/9/9 8:40
 */
public class LeetCode_261_1_以图判树 {
    public boolean validTree(int n, int[][] edges) {
        DSU dsu = new DSU(n);
        for (int[] edge : edges) {
            if (!dsu.union(edge[0], edge[1])) {
                return false;
            }
        }

        return dsu.parentCount() == 1;
    }

    class DSU {
        private int[] parent;
        private int[] rank;

        private DSU(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        private int find(int x) {
            if (parent[x] != x) {
                return find(parent[x]);
            }
            return parent[x];
        }

        private boolean union(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX == rootY) {
                return false;
            }

            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootY] > rank[rootX]) {
                parent[rootX] = rootY;
            } else {
                parent[rootX] = rootY;
                rank[rootY]++;
            }

            return true;
        }

        private int parentCount() {
            return new HashSet<>(Arrays.stream(parent).boxed().collect(Collectors.toList())).size();
        }
    }
}
