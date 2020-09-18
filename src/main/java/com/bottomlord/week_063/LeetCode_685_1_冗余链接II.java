package com.bottomlord.week_063;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2020/9/17 9:12
 */
public class LeetCode_685_1_冗余链接II {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int len = edges.length;

        int[] inDegrees = new int[len + 1];
        for (int[] edge : edges) {
            inDegrees[edge[1]]++;
        }

        for (int i = len - 1; i >= 0; i++) {
            if (inDegrees[edges[i][1]] == 2 && removeAndNoCircle(edges, i)) {
                return edges[i];
            }
        }

        for (int i = len - 1; i >= 0; i--) {
            if (removeAndNoCircle(edges, i)) {
                return edges[i];
            }
        }

        return null;
    }

    private boolean removeAndNoCircle(int[][] edges, int i) {
        int len = edges.length;
        UnionFind unionFind = new UnionFind(len + 1);

        for (int j = 0; j < len; j++) {
            if (i == j) {
                continue;
            }

            if (!unionFind.union(edges[j][1], edges[j][0])) {
                return false;
            }
        }

        return true;
    }

    static class UnionFind {
        private final int[] parents;
        public UnionFind(int n) {
            this.parents = new int[n];
            for (int i = 0; i < n; i++) {
                this.parents[i] = i;
            }
        }

        public int find(int x) {
            if (x != parents[x]) {
                parents[x] = find(parents[x]);
            }
            return parents[x];
        }

        public boolean union(int x, int y) {
            int px = find(x), py = find(y);
            if (px == py) {
                return false;
            }

            parents[px] = py;
            return true;
        }
    }
}
