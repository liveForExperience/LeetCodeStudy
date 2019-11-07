package com.bottomlord.week_018;

import com.bottomlord.DSU;

public class LeetCode_684_1_冗余连接 {
    public int[] findRedundantConnection(int[][] edges) {
        DSU dsu = new DSU(edges.length);
        int[] ans = new int[2];

        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];

            if (dsu.find(x - 1) == dsu.find(y - 1)) {
                ans[0] = x;
                ans[1] = y;
            } else {
                dsu.union(x - 1, y - 1);
            }
        }

        return ans;
    }
}
