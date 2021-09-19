package com.bottomlord.week_112;

/**
 * @author chen yue
 * @date 2021-08-30 08:50:39
 */
public class LeetCode_528_3 {
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
            return binarySearch(num);
        }

        private int binarySearch(int num) {
            int head = 0, tail = sum.length - 1;
            while (head < tail) {
                int mid = head + (tail - head) / 2;
                if (sum[mid] >= num) {
                    tail = mid;
                } else {
                    head = mid + 1;
                }
            }

            return head;
        }
    }
}
