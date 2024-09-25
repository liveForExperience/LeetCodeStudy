package com.bottomlord.week_150;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2022-05-26 22:50:27
 */
public class LeetCode_699_1_掉落的方块 {
    public List<Integer> fallingSquares(int[][] positions) {
        List<Integer> ans = new ArrayList<>();
        tr[1] = new Node();
        for (int[] position : positions) {
            int l = position[0], r = l + position[1] - 1, val = query(1, 1, N, l, r);
            update(1, 1, N, l, r, val + position[1]);
            ans.add(tr[1].val);
        }
        return ans;
    }

    class Node {
        private int l, r, val, add;
    }

    private Node[] tr = new Node[1000010];
    int N = (int)1e9, count = 0;

    private void update(int u, int start, int end, int l, int r, int val) {
        if (l <= start && r >= end) {
            tr[u].val = val;
            tr[u].add = val;
            return;
        }

        pushDown(u);

        int mid = start + end >> 1;

        if (l <= mid) {
            update(tr[u].l, start, mid, l, r, val);
        }

        if (r > mid) {
            update(tr[u].r, mid + 1, end, l, r, val);
        }

        pushUp(u);
    }

    private int query(int u, int start, int end, int l, int r) {
        if (l <= start && r >= end) {
            return tr[u].val;
        }

        pushDown(u);
        int mid = start + end >> 1;
        int ans = 0;
        if (l <= mid) {
            ans = query(tr[u].l, start, mid, l, r);
        }

        if (r > mid) {
            ans = Math.max(ans, query(tr[u].r, mid + 1, end, l, r));
        }

        return ans;
    }

    private void pushDown(int u) {
        if (tr[u] == null) {
            tr[u] = new Node();
        }

        if (tr[u].l == 0) {
            tr[u].l = ++count;
        }

        if (tr[u].r == 0) {
            tr[u].r = ++count;
        }

        if (tr[u].add == 0) {
            return;
        }

        int add = tr[u].add;
        tr[tr[u].l].val = add;
        tr[tr[u].l].add = add;
        tr[tr[u].r].val = add;
        tr[tr[u].r].add = add;

        tr[u].add = 0;
    }

    private void pushUp(int u) {
        tr[u].val = Math.max(tr[tr[u].l].val, tr[tr[u].r].val);
    }
}
