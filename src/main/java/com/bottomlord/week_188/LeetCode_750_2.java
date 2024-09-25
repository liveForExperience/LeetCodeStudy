package com.bottomlord.week_188;

/**
 * @author chen yue
 * @date 2023-02-18 11:03:17
 */
public class LeetCode_750_2 {
    public int countCornerRectangles(int[][] grid) {
        int c = grid[0].length, ans = 0;
        int[][] memo = new int[c][c];
        for (int[] row : grid) {
            for (int bottomRight = 0; bottomRight < c; bottomRight++) {
                if (row[bottomRight] == 0) {
                    continue;
                }

                for (int bottomLeft = 0; bottomLeft < bottomRight; bottomLeft++) {
                    if (row[bottomLeft] == 0) {
                        continue;
                    }

                    ans += memo[bottomLeft][bottomRight];
                    memo[bottomLeft][bottomRight]++;
                }
            }
        }

        return ans;
    }
}