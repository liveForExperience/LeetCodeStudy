package com.bottomlord.week_080;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author ChenYue
 * @date 2021/1/21 8:30
 */
public class LeetCode_1489_1_找到最小生成树里的关键边和伪关键边 {
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        int[][] newEdges = new int[n][4];
        for (int i = 0; i < edges.length; i++) {
            System.arraycopy(edges[i], 0, newEdges[i], 0, 3);
            newEdges[i][3] = i;
        }

        Arrays.sort(newEdges, Comparator.comparingInt(x -> x[2]));
        Uf uf = new Uf(n);
        int value = 0;
        for (int i = 0; i < edges.length; i++) {
            if (uf.union(newEdges[i][0], newEdges[i][1])) {
                value += newEdges[i][2];
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());
        ans.add(new ArrayList<>());

        for (int i = 0; i < edges.length; i++) {
            int v = 0;
            Uf uf1 = new Uf(n);
            for (int j = 0; j < edges.length; j++) {
                if (i != j && uf1.union(newEdges[j][0], newEdges[j][1])) {
                    v += newEdges[j][2];
                }
            }

            if (uf1.count != 1 || v > value) {
                ans.get(0).add(newEdges[i][3]);
                continue;
            }

            Uf uf2 = new Uf(n);
            uf2.union(newEdges[i][0], newEdges[i][1]);
            int v2 = newEdges[i][2];

            for (int j = 0; j < edges.length; j++) {
                if (i != j && uf2.union(newEdges[j][0], newEdges[j][1])) {
                    v2 += newEdges[j][2];
                }
            }

            if (uf2.count == 1 && v2 == value) {
                ans.get(1).add(newEdges[i][3]);
            }
        }

        return ans;
    }

    private static class Uf {
        private final int[] parent;
        private final int[] rank;
        private int count;

        public Uf(int n) {
            this.parent = new int[n];
            this.rank = new int[n];
            this.count = n;
            for (int i = 0; i < n; i++) {
                this.parent[i] = i;
                this.rank[i] = 1;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public boolean union(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX == rootY) {
                return false;
            }

            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                rank[rootX]++;
                parent[rootY] = rootX;
            }

            count--;
            return true;
        }
    }
}
