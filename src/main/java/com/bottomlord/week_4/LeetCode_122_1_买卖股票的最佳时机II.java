package com.bottomlord.week_4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author LiveForExperience
 * @date 2019/8/1 13:25
 */
public class LeetCode_122_1_买卖股票的最佳时机II {
    public int maxProfit(int[] prices) {
        List<Integer> ansList = new ArrayList<>();
        rescurse(prices, 0, false, 0, ansList);
        return Collections.max(ansList);
    }

    private void rescurse(int[] prices, int day, boolean has, int sum, List<Integer> ansList) {
        if (day == prices.length) {
            ansList.add(sum);
        }

        if (!has) {
            rescurse(prices, day + 1, true, sum - prices[day], ansList);
        } else {
            rescurse(prices, day + 1, false, sum + prices[day], ansList);
        }

        rescurse(prices, day + 1, has, sum, ansList);
    }
}
