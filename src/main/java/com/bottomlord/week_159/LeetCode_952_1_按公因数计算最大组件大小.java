package com.bottomlord.week_159;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2022-07-30 23:05:03
 */
public class LeetCode_952_1_按公因数计算最大组件大小 {
    public int largestComponentSize(int[] nums) {
        int max = Arrays.stream(nums).max().getAsInt();
        UnionFind uf = new UnionFind(max);

        for (int num : nums) {
            for (int i = 2; i * i <= num; i++) {
                if (num % i == 0) {
                    uf.union(num, i);
                    uf.union(num, num / i);
                }
            }
        }

        int ans = 0;
        int[] count = new int[max + 1];
        for (int num : nums) {
            int p = uf.find(num);
            count[p]++;

            ans = Math.max(ans, count[p]);
        }

        return ans;
    }

    private class UnionFind {
        private int[] parents, rank;

        public UnionFind(int max) {
            parents = new int[max + 1];
            rank = new int[max + 1];
            for (int i = 0; i <= max; i++) {
                parents[i] = i;
            }
        }

        public int find(int x) {
            if (parents[x] != x) {
                parents[x] = find(parents[x]);
            }

            return parents[x];
        }

        public void union(int x, int y) {
            int rx = find(x), ry = find(y);

            if (rx == ry) {
                return;
            }

            if (rank[rx] > rank[ry]) {
                parents[ry] = rx;
                rank[x]++;
            } else if (rank[rx] < rank[ry]) {
                parents[rx] = ry;
                rank[y]++;
            } else {
                parents[ry] = rx;
                rank[rx]++;
            }
        }
    }
}
