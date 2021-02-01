package com.bottomlord.week_082;

/**
 * @author ChenYue
 * @date 2021/2/1 8:49
 */
public class LeetCode_1579_1_保证图可完全遍历 {
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        UF ufa = new UF(n), ufb = new UF(n);
        for (int[] edge : edges) {
            edge[1]--;
            edge[2]--;
        }

        int ans = 0;

        for (int[] edge : edges) {
            if (edge[0] == 3) {
                if (!ufa.union(edge[1], edge[2])) {
                    ans++;
                } else {
                    ufb.union(edge[1], edge[2]);
                }
            }
        }

        for (int[] edge : edges) {
            if (edge[0] == 1) {
                if (!ufa.union(edge[1], edge[2])) {
                    ans++;
                }
            } else if (edge[0] == 2) {
                if (!ufb.union(edge[1], edge[2])) {
                    ans++;
                }
            }
        }

        if (ufa.count != 1 || ufb.count != 1) {
            return -1;
        }

        return ans;
    }

    static class UF {
        private int[] parent;
        private int[] rank;
        private int count;

        public UF(int n) {
            this.parent = new int[n];
            this.rank = new int[n];
            this.count = n;

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public boolean union(int x, int y) {
            int rx = find(x), ry = find(y);
            if (rx == ry) {
                return false;
            }

            if (rank[rx] < rank[ry]) {
                parent[rx] = parent[ry];
            } else if (rank[rx] > rank[ry]) {
                parent[ry] = parent[rx];
            } else {
                rank[rx]++;
                parent[ry] = parent[rx];
            }

            count--;
            return true;
        }
    }
}
