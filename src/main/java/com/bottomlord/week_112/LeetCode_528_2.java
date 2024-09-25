package com.bottomlord.week_112;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author chen yue
 * @date 2021-08-30 08:40:54
 */
public class LeetCode_528_2 {
    class Solution {
        private int[] sum;
        private int total;
        public Solution(int[] w) {
            sum = new int[w.length];
            sum[0] = w[0];
            for (int i = 1; i < w.length; i++) {
                sum[i] = sum[i - 1] + w[i];
            }

            total = sum[sum.length - 1];
        }

        public int pickIndex() {
            int num = (int) (Math.random() * total) + 1;
            for (int i = 0; i < sum.length; i++) {
                if (sum[i] >= num) {
                    return i;
                }
            }
            return 0;
        }
    }
}
