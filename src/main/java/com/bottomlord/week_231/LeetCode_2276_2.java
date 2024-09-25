package com.bottomlord.week_231;

/**
 * @author chen yue
 * @date 2023-12-16 23:13:41
 */
public class LeetCode_2276_2 {
    class CountIntervals {

        private CountIntervals left, right;
        private int cnt, l, r;

        public CountIntervals() {
            this.l = 1;
            this.r = (int) 10e9;
        }

        public CountIntervals(int l, int r) {
            this.l = l;
            this.r = r;
        }

        public void add(int left, int right) {
            if (this.cnt == this.r - this.l + 1) {
                return;
            }

            if (left <= l && r <= right) {
                cnt = this.r - this.l + 1;
                return;
            }

            int mid = l + (r - l) / 2;
            if (this.left == null) {
                this.left = new CountIntervals(l, mid);
            }

            if (this.right == null) {
                this.right = new CountIntervals(mid + 1, r);
            }

            if (left <= mid) {
                this.left.add(left, right);
            }

            if (right > mid) {
                this.right.add(left, right);
            }

            this.cnt = this.left.cnt + this.right.cnt;
        }

        public int count() {
            return cnt;
        }
    }
}
