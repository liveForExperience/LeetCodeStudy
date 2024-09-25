package com.bottomlord.week_016;

public class LeetCode_526_2 {
    private int count = 0;
    public int countArrangement(int N) {
        boolean[] visited = new boolean[N + 1];
        backTrack(N, 1, visited);
        return this.count;
    }

    private void backTrack(int n, int pos, boolean[] visited) {
        if (pos > n) {
            this.count++;
        }

        for (int i = 1; i <= n; i++) {
            if (!visited[i] && (pos % i == 0 || i % pos == 0)) {
                visited[i] = true;
                backTrack(n, pos + 1, visited);
                visited[i] = false;
            }
        }
    }
}