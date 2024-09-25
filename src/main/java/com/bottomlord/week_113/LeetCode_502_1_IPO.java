package com.bottomlord.week_113;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author chen yue
 * @date 2021-09-08 08:23:05
 */
public class LeetCode_502_1_IPO {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        boolean speedUp = true;
        for (int cost : capital) {
            if (cost > w) {
                speedUp = false;
                break;
            }
        }

        if (speedUp) {
            Arrays.sort(profits);
            int sum = 0;
            for (int i = profits.length - 1; i >= profits.length - Math.min(k, profits.length); i--) {
                sum += profits[i];
            }
            return sum + w;
        }

        for (int i = 0; i < Math.min(k, profits.length); i++) {
            int index = -1;
            for (int j = 0; j < profits.length; j++) {
                if (w >= capital[j]) {
                    if (index == -1 || profits[j] > profits[index]) {
                        index = j;
                    }
                }
            }

            if (index == -1) {
                break;
            }

            w += profits[index];
            capital[index] = Integer.MAX_VALUE;
        }

        return w;
    }
}
