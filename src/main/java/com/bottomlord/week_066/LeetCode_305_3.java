package com.bottomlord.week_066;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/10/18 20:52
 */
public class LeetCode_305_3 {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> ans = new ArrayList<>();
        if (m == 0 || n == 0 || positions == null || positions.length == 0) {
            return ans;
        }

        UnionFind uf = new UnionFind(m * n);
        for (int[] position : positions) {
            int r = position[0], c = position[1];
            List<Integer> overlap = new ArrayList<>();

            if (r >= 1 && uf.isValid((r - 1) * n + c)) {
                overlap.add((r - 1) * n + c);
            }

            if (r < (m - 1) && uf.isValid((r + 1) * n + c)) {
                overlap.add((r + 1) * n + c);
            }

            if (c >= 1 && uf.isValid(r * n + c - 1)) {
                overlap.add(r * n + c - 1);
            }

            if (c < (n - 1) && uf.isValid(r * n + c + 1)) {
                overlap.add(r * n + c + 1);
            }


            int index = r * n + c;
            if (uf.parent[index] == -1) {
                uf.setParent(index);

                for (int i : overlap) {
                    uf.union(i, index);
                }
            }

            ans.add(uf.count);
        }

        return ans;
    }

    class UnionFind {
        private int count;
        private int[] parent;
        private int[] rank;

        public UnionFind(int n) {
            parent = new int[n];
            Arrays.fill(parent, -1);
            rank = new int[n];
            count = 0;
        }

        public int count() {
            return count;
        }

        public boolean isValid(int n) {
            return parent[n] >= 0;
        }

        public void setParent(int n) {
            parent[n] = n;
            count++;
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }

            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x),
                rootY = find(y);

            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if(rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                rank[rootX]++;
                parent[rootY] = rootX;
            }

            count--;
        }
    }
}
