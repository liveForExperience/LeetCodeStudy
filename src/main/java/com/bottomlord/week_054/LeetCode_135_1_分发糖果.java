package com.bottomlord.week_054;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2020/7/14 8:49
 */
public class LeetCode_135_1_分发糖果 {
    public int candy(int[] ratings) {
        int len = ratings.length;
        if (len == 0) {
            return 0;
        }

        boolean change = false;
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);

        do {
            change = false;

            for (int i = 0; i < len; i++) {
                if (i != 0 && ratings[i] > ratings[i - 1] && candies[i] <= candies[i - 1]) {
                    candies[i] = candies[i - 1] + 1;
                    change = true;
                }

                if (i != len - 1 && ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1]) {
                    candies[i] = candies[i + 1] + 1;
                    change = true;
                }
            }

        } while (change);

        return Arrays.stream(candies).sum();
    }
}
