package com.bottomlord.week_062;

/**
 * @author ChenYue
 * @date 2020/9/7 9:07
 */
public class LeetCode_256_2 {
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }

        Integer[][] memo = new Integer[costs.length][3];
        return Math.min(recurse(costs, 0, 0, memo), Math.min(recurse(costs, 0, 1, memo), recurse(costs, 0, 2, memo)));
    }

    private int recurse(int[][] costs, int depth, int color, Integer[][] memo) {
        int total = costs[depth][color];
        if (depth == costs.length - 1) {
            memo[depth][color] = total;
            return total;
        }

        if (color == 0) {
            int green = memo[depth + 1][1] == null ? recurse(costs, depth + 1, 1, memo) : memo[depth + 1][1],
                blue = memo[depth + 1][2] == null ? recurse(costs, depth + 1, 2, memo) : memo[depth + 1][2];
            total += Math.min(green, blue);
        } else if (color == 1) {
            int red = memo[depth + 1][0] == null ? recurse(costs, depth + 1, 0, memo) : memo[depth + 1][0],
                blue = memo[depth + 1][2] == null ? recurse(costs, depth + 1, 2, memo) : memo[depth + 1][2];
            total += Math.min(red, blue);
        } else {
            int red = memo[depth + 1][0] == null ? recurse(costs, depth + 1, 0, memo) : memo[depth + 1][0],
                green = memo[depth + 1][1] == null ? recurse(costs, depth + 1, 1, memo) : memo[depth + 1][1];
            total += Math.min(red, green);
        }

        memo[depth][color] = total;
        return total;
    }
}
