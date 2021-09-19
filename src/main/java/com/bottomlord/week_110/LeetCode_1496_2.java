package com.bottomlord.week_110;

/**
 * @author chen yue
 * @date 2021-08-17 08:54:26
 */
public class LeetCode_1496_2 {
    public boolean isPathCrossing(String path) {
        boolean[][] memo = new boolean[2001][2001];
        int row = 0, col = 0;
        memo[row + 1000][col + 1000] = true;

        char[] cs = path.toCharArray();
        for (char c : cs) {
            if (c == 'N') {
                row--;
            } else if (c == 'S') {
                row++;
            } else if (c == 'E') {
                col++;
            } else {
                col--;
            }

            if (memo[row + 1000][col + 1000]) {
                return true;
            }

            memo[row + 1000][col + 1000] = true;
        }

        return false;
    }
}
