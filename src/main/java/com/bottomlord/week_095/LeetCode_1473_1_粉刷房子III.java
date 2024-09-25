package com.bottomlord.week_095;

/**
 * @author ChenYue
 * @date 2021/5/4 20:28
 */
public class LeetCode_1473_1_粉刷房子III {
    private int m, target, minCost;
    private int[] houses;
    private int[][] costs;

    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        this.houses = houses;
        this.costs = cost;
        this.m = m;
        this.target = target;
        this.minCost = Integer.MAX_VALUE;

        dfs(0, -1, 0, 0);
        return minCost == Integer.MAX_VALUE ? -1 : minCost;
    }

    private void dfs(int index, int preColorIndex, int sumCost, int blockCount) {
        if (index > m || sumCost > minCost || blockCount > target) {
            return;
        }

        if (index == m) {
            if (target == blockCount) {
                minCost = sumCost;
            }
            return;
        }

        if (houses[index] != 0) {
            dfs(index + 1, houses[index] - 1, sumCost, preColorIndex == -1 ? 1 : preColorIndex + 1 == houses[index] ? blockCount : blockCount + 1);
            return;
        }

        int[] cost = costs[index];
        for (int i = 0; i < cost.length; i++) {
            if (i == preColorIndex) {
                dfs(index + 1, i, sumCost + cost[i], blockCount);
            } else {
                dfs(index + 1, i, sumCost + cost[i], blockCount + 1);
            }
        }
    }
}
