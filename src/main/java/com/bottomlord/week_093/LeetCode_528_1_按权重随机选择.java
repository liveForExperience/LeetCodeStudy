package com.bottomlord.week_093;

import java.util.Random;

/**
 * @author ChenYue
 * @date 2021/4/22 17:04
 */
public class LeetCode_528_1_按权重随机选择 {
    class Solution {
        private Random random = new Random();
        private int[] sums;
        public Solution(int[] w) {
            int len = w.length;
            this.sums = new int[len];

            sums[0] = w[0];
            for (int i = 1; i < len; i++) {
                sums[i] = sums[i - 1] + w[i];
            }
        }

        public int pickIndex() {
            int target = random.nextInt(sums[sums.length - 1]);

            int head = 0, tail = sums.length - 1;
            while (head < tail) {
               int mid = head + (tail - head) / 2;

               if (target >= sums[mid]) {
                   head = mid + 1;
               } else {
                   tail = mid;
               }
            }

            return head;
        }
    }
}
