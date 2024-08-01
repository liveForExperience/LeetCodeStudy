package com.bottomlord.week_265;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2024-08-01 10:40:52
 */
public class LeetCode_LCP40_1_心算挑战 {
    public int maxmiumScore(int[] cards, int cnt) {
        Arrays.sort(cards);
        int len = cards.length, start = len - cnt,
            minEven = -1, minOdd = -1, sum = 0;

        for (int i = start; i < cards.length; i++) {
            int num = cards[i];
            sum += num;

            if (minOdd != -1 && minEven != -1) {
                continue;
            }

            if (minOdd == -1 && num % 2 == 1) {
                minOdd = num;
            }

            if (minEven == -1 && num % 2 == 0) {
                minEven = num;
            }
        }

        if (sum % 2 == 0) {
            return sum;
        }

        int maxEven = -1, maxOdd = -1;
        for (int i = start - 1; i >= 0; i--) {
            int num = cards[i];

            if (maxOdd != -1 && maxEven != -1) {
                continue;
            }

            if (maxOdd == -1 && num % 2 == 1) {
                maxOdd = num;
            }

            if (maxEven == -1 && num % 2 == 0) {
                maxEven = num;
            }
        }

        int sum1 = 0, sum2 = 0;
        if (minOdd != -1 && maxEven != -1) {
            sum1 = sum - minOdd + maxEven;
        }

        if (minEven != -1 && maxOdd != -1) {
            sum2 = sum - minEven + maxOdd;
        }

        return Math.max(sum1, sum2);
    }
}
