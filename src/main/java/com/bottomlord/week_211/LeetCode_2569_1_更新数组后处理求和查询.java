package com.bottomlord.week_211;

/**
 * @author chen yue
 * @date 2023-07-27 20:43:43
 */
public class LeetCode_2569_1_更新数组后处理求和查询 {
    public long[] handleQuery(int[] nums1, int[] nums2, int[][] queries) {
        int num3 = 0;
        for (int[] query : queries) {
            if (query[0] == 3) {
                num3++;
            }
        }

        long[] ans = new long[num3];
        int index = 0;

        long sum = 0;
        for (int num : nums2) {
            sum += num;
        }

        SegmentTree tree = new SegmentTree(nums1);
        for (int[] query : queries) {
            int operator = query[0];
            if (operator == 1) {
                tree.modify(1, query[1] + 1, query[2] + 1);
            } else if (operator == 2) {
                sum += (long) query[1] * tree.query(1, 1, nums2.length);
            } else {
                ans[index++] = sum;
            }
        }

        return ans;
    }

    private static class SegmentTree {
        private Node[] nodes;
        private int[] nums;

        public SegmentTree(int[] nums) {
            int n = nums.length;
            this.nums = nums;
            nodes = new Node[n << 2];
            for (int i = 0; i < nodes.length; i++) {
                nodes[i] = new Node();
            }
            build(1, 1, n);
        }

        private void build(int u, int l, int r) {
            nodes[u].l = l;
            nodes[u].r = r;
            if (l == r) {
                nodes[u].s = nums[l - 1];
                return;
            }

            int mid = (l + r) >> 1;
            build(u << 1, l, mid);
            build(u << 1 | 1, mid + 1, r);
            pushUp(u);
        }

        private void modify(int u, int l, int r) {
            if (nodes[u].l >= l && nodes[u].r <= r) {
                nodes[u].lazy ^= 1;
                nodes[u].s = nodes[u].r - nodes[u].l + 1 - nodes[u].s;
                return;
            }
            pushDown(u);

            int mid = (nodes[u].r + nodes[u].l) >> 1;
            if (l <= mid) {
                modify(u << 1, l, r);
            }
            if (r > mid) {
                modify(u << 1 | 1, l, r);
            }
            pushUp(u);
        }

        private int query(int u, int l, int r) {
            if (nodes[u].l >= l && nodes[u].r <= r) {
                return nodes[u].s;
            }

            pushDown(u);
            int mid = (nodes[u].l + nodes[u].r) >> 1;
            int sum = 0;
            if (l <= mid) {
                sum += query(u << 1, l, r);
            }

            if (r > mid) {
                sum += query(u << 1 | 1, l, r);
            }
            return sum;
        }

        private void pushDown(int u) {
            if (nodes[u].lazy != 1) {
                return;
            }

            int mid = (nodes[u].l + nodes[u].r) >> 1;
            nodes[u << 1].s = mid - nodes[u].l + 1 - nodes[u << 1].s;
            nodes[u << 1].lazy ^= 1;
            nodes[u << 1 | 1].s = nodes[u].r - mid - nodes[u << 1 | 1].s;
            nodes[u << 1 | 1].lazy ^= 1;
            nodes[u].lazy ^= 1;
        }

        private void pushUp(int u) {
            nodes[u].s = nodes[u << 1].s + nodes[u << 1 | 1].s;
        }
    }

    private static class Node {
        private int l, r, s, lazy;
    }
}
