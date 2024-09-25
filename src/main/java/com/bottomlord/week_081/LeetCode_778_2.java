package com.bottomlord.week_081;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LeetCode_778_2 {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int index = i * n + j;
                if (i > 0) {
                    list.add(new int[]{index - n, index, Math.max(grid[i - 1][j], grid[i][j])});
                }

                if (j > 0) {
                    list.add(new int[]{index - 1, index, Math.max(grid[i][j - 1], grid[i][j])});
                }
            }
        }

        list.sort(Comparator.comparingInt(x -> x[2]));

        UF uf = new UF(n * n);
        for (int i = 0; i < list.size(); i++) {
            int x = list.get(i)[0], y = list.get(i)[1], v = list.get(i)[2];
            uf.union(x, y);
            if (uf.isConnected(0, n * n - 1)) {
                return v;
            }
        }

        return n * n - 1;
    }

    static class UF {
        private int[] parents;

        public UF(int n) {
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

        public void union(int x, int y) {
            parents[find(x)] = find(y);
        }

        public boolean isConnected(int x, int y) {
            return find(x) == find(y);
        }
    }
}
