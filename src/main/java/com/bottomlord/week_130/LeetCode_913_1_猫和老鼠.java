package com.bottomlord.week_130;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2022-01-04 08:56:50
 */
public class LeetCode_913_1_猫和老鼠 {
    private int[][] graph;
    private int[][][] dp;
    private int n;

    public int catMouseGame(int[][] graph) {
        this.n = graph.length;
        this.graph = graph;
        this.dp = new int[n][n][2 * n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        return getResult(1, 2, 0);
    }

    private int getResult(int mouse, int cat, int turn) {
        if (turn == 2 * n) {
            return 0;
        }

        if (dp[mouse][cat][turn] < 0) {
            if (mouse == 0) {
                dp[mouse][cat][turn] = 1;
            } else if (mouse == cat) {
                dp[mouse][cat][turn] = 2;
            } else {
                getNextResult(mouse, cat, turn);
            }
        }

        return dp[mouse][cat][turn];
    }

    private void getNextResult(int mouse, int cat, int turn) {
        int curMove = turn % 2 == 0 ? mouse : cat;
        int defaultResult = curMove == mouse ? 2 : 1;
        int result = defaultResult;

        for (int next : graph[curMove]) {
            if (curMove == cat && next == 0) {
                continue;
            }

            int nextMouse = curMove == mouse ? next : mouse;
            int nextCat = curMove == cat ? next : cat;
            int nextResult = getResult(nextMouse, nextCat, turn + 1);

            if (nextResult != defaultResult) {
                result = nextResult;
                if (result != 0) {
                    break;
                }
            }
        }

        dp[mouse][cat][turn] = result;
    }
}
