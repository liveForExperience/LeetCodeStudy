package com.bottomlord.week_083;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2021/2/14 12:37
 */
public class LeetCode_765_1_情侣牵手 {
    public int minSwapsCouples(int[] row) {
        int len = row.length, total = len / 2;
        Uf uf = new Uf(total);

        for (int i = 0; i < len / 2; i += 2) {
            int x = row[i] / 2, y = row[i + 1] / 2;
            uf.union(x, y);
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < total; i++) {
            int r = uf.find(i);
            map.put(r, map.getOrDefault(r, 0) + 1);
        }

        int ans = 0;
        for (Integer value : map.values()) {
            ans += (value - 1);
        }
        return ans;
    }

    private static class Uf {
        private int[] parent;
        private int[] rank;

        public Uf(int n) {
            parent = new int[n];
            rank = new int[n];
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

        public void union(int x, int y) {
            int rx = find(x), ry = find(y);

            if (rx == ry) {
                return;
            }

            if (rank[rx] < rank[ry]) {
                parent[rx] = ry;
            } else if (rank[rx] > rank[ry]) {
                parent[ry] = rx;
            } else {
                parent[ry] = rx;
                rank[rx]++;
            }
        }
    }
}
