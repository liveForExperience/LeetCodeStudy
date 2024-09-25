package com.bottomlord.week_129;

/**
 * @author chen yue
 * @date 2022-01-02 20:59:08
 */
public class LeetCode_1971_2 {
    public boolean validPath(int n, int[][] edges, int start, int end) {
        Dsu dsu = new Dsu(n);
        for (int[] edge : edges) {
            dsu.union(edge[0], edge[1]);
        }

        return dsu.find(start) == dsu.find(end);
    }

    private class Dsu {
        private int[] parent;

        public Dsu(int n) {
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }

            return parent[x];
        }

        public void union(int x, int y) {
            parent[parent[x]] = find(y);
        }
    }
}