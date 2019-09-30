package com.bottomlord.week_013;

import com.bottomlord.DSU;

public class LeetCode_959_1_由斜杠划分区域 {
    public int regionsBySlashes(String[] grid) {
        int len = grid.length;
        DSU dsu = new DSU(4 * len * len);

        for (int r = 0; r < len; r++) {
            for (int c = 0; c < len; c++) {
                int root = 4 * (r * len + c);
                char val = grid[r].charAt(c);

                if (val != '\\') {
                    dsu.union(root, root + 3);
                    dsu.union(root + 1, root + 2);
                }

                if (val != '/') {
                    dsu.union(root, root + 1);
                    dsu.union(root + 2, root + 3);
                }

                if (r < len - 1) {
                    dsu.union(root + 2, root + 4 * len);
                }

                if (r > 0) {
                    dsu.union(root, root - 4 * len + 2);
                }

                if (c > 0) {
                    dsu.union(root + 3, root - 3);
                }

                if (c < len - 1) {
                    dsu.union(root + 1, root + 7);
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < 4 * len * len; i++) {
            if (dsu.find(i) == i) {
                ans++;
            }
        }
        return ans;
    }
}
