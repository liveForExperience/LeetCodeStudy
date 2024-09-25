package com.bottomlord.week_006;

public class LeetCode_997_1_找到小镇的法官 {
    public int findJudge(int N, int[][] trust) {
        int[][] graph = new int[N][2];
        for (int[] arr : trust) {
            int a = arr[0];
            int b = arr[1];

            graph[a - 1][0]++;
            graph[b - 1][1]++;
        }

        for (int i = 0; i < graph.length; i++) {
            if (graph[i][0] == 0 && graph[i][1] == N - 1) {
                return i + 1;
            }
        }

        return -1;
    }
}
