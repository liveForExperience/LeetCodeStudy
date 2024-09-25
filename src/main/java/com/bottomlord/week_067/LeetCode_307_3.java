package com.bottomlord.week_067;

/**
 * @author ChenYue
 * @date 2020/10/21 8:22
 */
public class LeetCode_307_3 {
    class NumArray {
        private int[] tree;
        private int n;
        public NumArray(int[] nums) {
            n = nums.length;
            tree = new int[2 * n];
            for (int i = 0, j = n; j < 2 * n; i++, j++) {
                tree[j] = nums[i];
            }

            for (int i = n - 1; i > 0; i--) {
                tree[i] = tree[2 * i] + tree[2 * i + 1];
            }
        }

        public void update(int i, int val) {
            i += n;
            tree[i] = val;
            while (i > 0) {
                int l = i % 2 == 0 ? i : i - 1,
                    r = i % 2 == 0 ? i + 1 : i;
                i /= 2;
                tree[i] = tree[l] + tree[r];
            }
        }

        public int sumRange(int i, int j) {
            int l = i += n, r = j += n;
            int sum = 0;
            while (l <= r) {
                if (l % 2 == 1) {
                    sum += tree[l++];
                }

                if (r % 2 == 0) {
                    sum += tree[r--];
                }

                l /= 2;
                r /= 2;
            }

            return sum;
        }
    }
}
