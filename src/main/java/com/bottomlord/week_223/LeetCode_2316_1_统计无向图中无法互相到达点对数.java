package com.bottomlord.week_223;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2023-10-21 15:52:15
 */
public class LeetCode_2316_1_统计无向图中无法互相到达点对数 {
    public long countPairs(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            uf.union(edge[0], edge[1]);
        }

        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += n - uf.size(uf.find(i));
        }

        return sum / 2;
    }

    private static class UnionFind {

        private int[] parent, size;

        public UnionFind(int n) {
            this.parent = new int[n];
            this.size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public void union(int x, int y) {
            int rx = find(x), ry = find(y);
            if (rx != ry) {
                if (size(rx) > size(ry)) {
                    parent[ry] = rx;
                    size[rx] += size[ry];
                } else {
                    parent[rx] = ry;
                    size[ry] += size[rx];
                }
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }

            return parent[x];
        }

        public int size(int x) {
            return size[x];
        }
    }
}
