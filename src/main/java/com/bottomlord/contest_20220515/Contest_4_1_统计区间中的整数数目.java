package com.bottomlord.contest_20220515;

/**
 * @author chen yue
 * @date 2022-05-15 10:24:22
 */
public class Contest_4_1_统计区间中的整数数目 {
    class CountIntervals {
        CountIntervals left, right;
        int l, r, sum;

        public CountIntervals() {
            l = 1;
            r = (int) 1e9;
        }

        CountIntervals(int l, int r) {
            this.l = l;
            this.r = r;
        }

        public void add(int L, int R) {
            if (sum == r - l + 1) {
                return;
            }

            if (L <= l && r <= R) {
                sum = r - l + 1;
                return;
            }

            int mid = (l + r) / 2;
            if (left == null) {
                left = new CountIntervals(l, mid);
            }

            if (right == null) {
                right = new CountIntervals(mid + 1, r);
            }

            if (L <= mid) {
                left.add(L, R);
            }

            if (R > mid) {
                right.add(L, R);
            }

            sum = left.sum + right.sum;
        }

        public int count() {
            return sum;
        }
    }
}
