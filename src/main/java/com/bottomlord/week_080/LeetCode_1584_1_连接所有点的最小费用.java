package com.bottomlord.week_080;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author ChenYue
 * @date 2021/1/20 8:29
 */
public class LeetCode_1584_1_连接所有点的最小费用 {
    public int minCostConnectPoints(int[][] points) {
        int len = points.length;

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                edges.add(new Edge(dist(points, i, j), i, j));
            }
        }

        edges.sort(Comparator.comparingInt(x -> x.len));

        Uf uf = new Uf(len);
        int ans = 0, num = 1;
        for (Edge edge : edges) {
            if (uf.union(edge.x, edge.y)) {
                ans += edge.len;
                num++;

                if (num == len) {
                    break;
                }
            }
        }

        return ans;
    }

    private int dist(int[][] points, int i, int j) {
        return Math.abs(points[i][0]  - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
    }

    private static class Edge {
        private int len, x, y;
        public Edge(int len, int x, int y) {
            this.len = len;
            this.x = x;
            this.y = y;
        }
    }

    private static class Uf {
        private int[] parent;
        private int[] rank;

        public Uf(int n) {
            this.parent = new int[n];
            this.rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        private int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        private boolean union(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX == rootY) {
                return false;
            }

            if (rank[rootX] < rank[rootY]) {
                parent[rootY] = parent[rootX];
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootX] = parent[rootY];
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }

            return true;
        }
    }
}
