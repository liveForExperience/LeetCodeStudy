package com.bottomlord.week_007;

public class LeetCode_1042_2 {
    public int[] gardenNoAdj(int N, int[][] paths) {
        int[][] topo = new int[N + 1][3];
        for (int[] path : paths) {
            int index = 0;
            while (topo[path[0]][index] != 0) {
                index++;
            }
            topo[path[0]][index] = path[1];

            index = 0;
            while (topo[path[1]][index] != 0) {
                index++;
            }
            topo[path[1]][index] = path[0];
        }

        int[] ans = new int[N];
        for (int i = 1; i < N + 1; i++) {
             boolean[] used = new boolean[5];
             for (int j = 0; j < topo[i].length; j++) {
                 if (topo[i][j] != 0 && ans[topo[i][j] - 1] != 0) {
                     used[ans[topo[i][j] - 1]] = true;
                 }
             }

             for (int j = 1; j < 5; j++) {
                 if (!used[j]) {
                     ans[i - 1] = j;
                     break;
                 }
             }
        }

        return ans;
    }
}