package com.bottomlord.week_176;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chen yue
 * @date 2022-12-04 10:03:34
 */
public class LeetCode_1774_1_最接近目标价格的甜点成本 {
    private int target, min = Integer.MAX_VALUE, ans = 0;
    private int[] toppingCosts;

    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        this.target = target;
        this.toppingCosts = toppingCosts;

        for (int baseCost : baseCosts) {
            backTrack(baseCost, new int[toppingCosts.length], new HashSet<>());
        }

        return ans;
    }

    private void backTrack(int cost, int[] count, Set<Integer> memo) {
        if (!memo.add(cost)) {
            return;
        }

        int diff = Math.abs(cost - target);

        if (diff == min) {
            ans = Math.min(cost, ans);
        }

        if (diff < min) {
            min = diff;
            ans = cost;
        }

        for (int i = 0; i < toppingCosts.length; i++) {
            if (count[i] >= 2) {
                continue;
            }

            int toppingCost = toppingCosts[i];
            count[i]++;
            cost += toppingCost;

            backTrack(cost, count, memo);

            count[i]--;
            cost -= toppingCost;
        }
    }
}
