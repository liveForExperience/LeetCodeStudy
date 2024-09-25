package com.bottomlord.week_186;

import java.util.*;

/**
 * @author chen yue
 * @date 2023-01-25 15:44:54
 */
public class LeetCode_1632_1_矩阵转换后的秩 {
    public int[][] matrixRankTransform(int[][] matrix) {
        int r = matrix.length, c = matrix[0].length;
        Uf uf = new Uf(r * c);

        for (int i = 0; i < r; i++) {
            Map<Integer, List<Integer>> map = new HashMap<>();
            for (int j = 0; j < c; j++) {
                int index = i * c + j, val = matrix[i][j];
                map.computeIfAbsent(val, x -> new ArrayList<>()).add(index);
            }

            for (List<Integer> list : map.values()) {
                if (list.size() < 2) {
                    continue;
                }

                for (int k = 1; k < list.size(); k++) {
                    uf.union(list.get(k - 1), list.get(k));
                }
            }
        }

        for (int j = 0; j < c; j++) {
            Map<Integer, List<Integer>> map = new HashMap<>();
            for (int i = 0; i < r; i++) {
                int index = i * c + j, val = matrix[i][j];
                map.computeIfAbsent(val, x -> new ArrayList<>()).add(index);
            }

            for (List<Integer> list : map.values()) {
                if (list.size() < 2) {
                    continue;
                }

                for (int k = 1; k < list.size(); k++) {
                    uf.union(list.get(k - 1), list.get(k));
                }
            }
        }

        List<Integer>[] adjs = new ArrayList[r * c];
        for (int i = 0; i < adjs.length; i++) {
            adjs[i] = new ArrayList<>();
        }

        int[] inDegrees = new int[r * c];
        for (int i = 0; i < r; i++) {
            int[][] arr = new int[c][2];
            for (int j = 0; j < c; j++) {
                arr[j][0] = matrix[i][j];
                arr[j][1] = j;
            }

            Arrays.sort(arr, Comparator.comparingInt(x -> x[0]));
            for (int j = 1; j < c; j++) {
                if (arr[j - 1][0] != arr[j][0]) {
                    int u = uf.find(i * c + arr[j - 1][1]);
                    int v = uf.find(i * c + arr[j][1]);
                    inDegrees[v]++;
                    adjs[u].add(v);
                }
            }
        }

        for (int j = 0; j < c; j++) {
            int[][] arr = new int[r][2];
            for (int i = 0; i < r; i++) {
                arr[i][0] = matrix[i][i];
                arr[i][1] = i;
            }

            Arrays.sort(arr, Comparator.comparingInt(x -> x[0]));
            for (int i = 1; i < r; i++) {
                if (arr[i - 1][0] != arr[i][0]) {
                    int u = uf.find(arr[i - 1][1] * c + j);
                    int v = uf.find(arr[i][1] * c + j);
                    inDegrees[v]++;
                    adjs[u].add(v);
                }
            }
        }

        int[] res = new int[r * c];
        Arrays.fill(res, 1);
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < r * c; i++) {
            if (uf.find(i) == i && inDegrees[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (Integer v : adjs[u]) {
                res[v] = Math.max(res[v], res[u] + 1);
                if (--inDegrees[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        int[][] ans = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                ans[i][j] = res[uf.find(i * c + j)];
            }
        }

        return ans;
    }

    class Uf {
        private int[] parent;

        public Uf(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] == x) {
                return x;
            }
            return parent[x] = find(parent[x]);
        }

        public void union(int x, int y) {
            int rx = find(x), ry = find(y);
            if (rx == ry) {
                return;
            }
            parent[ry] = rx;
        }
    }
}
