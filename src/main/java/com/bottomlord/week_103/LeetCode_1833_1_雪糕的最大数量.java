package com.bottomlord.week_103;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2021/7/2 8:12
 */
public class LeetCode_1833_1_雪糕的最大数量 {
    public int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);
        int count = 0;
        for (int cost : costs) {
            coins -= cost;
            if (coins < 0) {
                return count;
            }
            count++;
        }
        return count;
    }
}
