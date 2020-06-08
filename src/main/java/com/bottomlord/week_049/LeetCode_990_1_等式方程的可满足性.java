package com.bottomlord.week_049;

import com.bottomlord.DSU;

/**
 * @author ChenYue
 * @date 2020/6/8 8:20
 */
public class LeetCode_990_1_等式方程的可满足性 {
    public boolean equationsPossible(String[] equations) {
        Dsu dsu = new Dsu(26);

        for (String equation : equations) {
            if (equation.charAt(1) == '=') {
                int x = equation.charAt(0) - 'a';
                int y = equation.charAt(3) - 'a';

                dsu.union(x, y);
            }
        }

        for (String equation : equations) {
            if (equation.charAt(1) != '=') {
                int x = equation.charAt(0) - 'a';
                int y = equation.charAt(3) - 'a';

                if (dsu.find(x) == dsu.find(y)) {
                    return false;
                }
            }
        }

        return true;
    }

    private class Dsu {
        private int[] parent;

        public Dsu(int n) {
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }

            return parent[x];
        }

        public void union(int x, int y) {
            parent[find(x)] = find(y);
        }
    }
}