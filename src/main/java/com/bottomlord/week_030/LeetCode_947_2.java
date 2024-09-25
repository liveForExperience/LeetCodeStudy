package com.bottomlord.week_030;

/**
 * @author ThinkPad
 * @date 2020/1/30 12:20
 */
public class LeetCode_947_2 {
    public int removeStones(int[][] stones) {
        int count = 0, len = stones.length;
        boolean[] visited = new boolean[len];

        for (int i = 0; i < len; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(visited, stones[i][0], stones[i][1], stones);
                count++;
            }
        }

        return len - count;
    }

    private void dfs(boolean[] visited, int x, int y, int[][] stones) {
        for (int i = 0; i < stones.length; i++) {
            if ((stones[i][0] == x || stones[i][1] == y) && !visited[i]) {
                visited[i] = true;
                dfs(visited, stones[i][0], stones[i][1], stones);
            }
        }
    }
}