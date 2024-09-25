package com.bottomlord.week_037;

import java.util.Arrays;

/**
 * @author ThinkPad
 * @date 2020/3/22 19:33
 */
public class LeetCode_945_2 {

    public int minIncrementForUnique(int[] A) {
        UnionFind unionFind = new UnionFind();
        int ans = 0;
        for (int num : A) {
            if (unionFind.contains(num)) {
                int root = unionFind.find(num);
                ans += root - num + 1;
                unionFind.init(root + 1);
            } else {
                unionFind.init(num);
            }
        }

        return ans;
    }

    private class UnionFind {
        private int[] parent;

        public UnionFind() {
            parent = new int[79999];
            Arrays.fill(parent, -1);
        }

        public void init(int num) {
            parent[num] = num;

            if (num > 0 && parent[num - 1] != -1) {
                union(num - 1, num);
            }

            if (parent[num + 1] != -1) {
                union(num, num + 1);
            }
        }

        public boolean contains(int num) {
            return parent[num] != -1;
        }

        public int find(int num) {
            if (num != parent[num]) {
                parent[num] = find(parent[num]);
            }

            return parent[num];
        }

        public void union(int x, int y) {
            parent[parent[x]] = find(y);
        }
    }
}
