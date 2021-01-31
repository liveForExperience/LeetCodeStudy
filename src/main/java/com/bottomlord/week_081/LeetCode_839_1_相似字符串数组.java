package com.bottomlord.week_081;

public class LeetCode_839_1_相似字符串数组 {
    public int numSimilarGroups(String[] strs) {
        int len = strs.length;

        UF uf = new UF(len);

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (like(strs[i], strs[j])) {
                    uf.union(i, j);
                }
            }
        }

        return uf.count;
    }

    private boolean like(String x, String y) {
        int xl = x.length(), yl = y.length();
        if (xl != yl) {
            return false;
        }

        char xc = ' ', yc = ' ';
        int diff = 0;
        for (int i = 0; i < xl; i++) {
            if (x.charAt(i) != y.charAt(i)) {
                diff++;

                if (diff > 2) {
                    return false;
                }

                if (diff == 1) {
                    xc = x.charAt(i);
                    yc = y.charAt(i);
                }

                if (diff == 2) {
                    if (x.charAt(i) != yc || y.charAt(i) != xc) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    static class UF {
        private int[] parents;
        private int[] rank;
        private int count;

        public UF(int n) {
            this.parents = new int[n];
            this.rank = new int[n];
            this.count = n;

            for (int i = 0; i < n; i++) {
                this.parents[i] = i;
                this.rank[i] = 1;
            }
        }

        public int find(int x) {
            if (x != parents[x]) {
                parents[x] = find(parents[x]);
            }
            return parents[x];
        }

        public void union(int x, int y) {
            int rx = find(x), ry = find(y);

            if (rx == ry) {
                return;
            }

            if (rank[x] > rank[y]) {
                parents[ry] = rx;
            } else if (rank[x] < rank[y]) {
                parents[rx] = ry;
            } else {
                rank[rx]++;
                parents[ry] = rx;
            }

            count--;
        }
    }
}
