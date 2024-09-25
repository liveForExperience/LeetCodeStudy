package com.bottomlord.contest_20220703;

import com.bottomlord.ListNode;

/**
 * @author chen yue
 * @date 2022-07-02 23:29:02
 */
public class Contest_2_1_ {
    private int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private int m, n;
    public int[][] spiralMatrix(int m, int n, ListNode head) {
        int[][] ans = new int[m][n];
        this.m = m;
        this.n = n;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans[i][j] = -1;
            }
        }
        boolean[][] memo = new boolean[m][n];

        dfs(ans, 0, 0, 0, head, memo);
        return ans;
    }

    private void dfs(int[][] ans, int x, int y, int di, ListNode node, boolean[][] memo) {
        if (node == null) {
            return;
        }

        ans[x][y] = node.val;
        memo[x][y] = true;
        int nx = x + dirs[di][0], ny = y + dirs[di][1];

        if (nx < 0 || nx >= m || ny < 0 || ny >= n || memo[nx][ny]) {
            di = (di + 1) % 4;
            nx = x + dirs[di][0];
            ny = y + dirs[di][1];
        }

        dfs(ans, nx, ny, di, node.next, memo);
    }
}
