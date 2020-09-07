package com.bottomlord.week_062;

/**
 * @author ChenYue
 * @date 2020/9/7 8:26
 */
public class LeetCode_256_1_粉刷房子 {
    public int minCost(int[][] costs) {
        if (costs.length == 0) {
            return 0;
        }

        return Math.min(recurse(costs, 0, 0), Math.min(recurse(costs, 0, 1), recurse(costs, 0, 2)));
    }

    private int recurse(int[][] costs, int depth, int color) {
        int total = costs[depth][color];
        if (depth == costs.length - 1) {
            return total;
        }

        if (color == 0) {
            total += Math.min(recurse(costs, depth + 1, 1), recurse(costs, depth + 1, 2));
        } else if (color == 1) {
            total += Math.min(recurse(costs, depth + 1, 0), recurse(costs, depth + 1, 2));
        } else {
            total += Math.min(recurse(costs, depth + 1, 1), recurse(costs, depth + 1, 0));
        }

        return total;
    }
}
