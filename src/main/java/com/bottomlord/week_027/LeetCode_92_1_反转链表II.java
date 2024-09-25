package com.bottomlord.week_027;

import com.bottomlord.ListNode;

/**
 * @author ThinkPad
 * @date 2020/1/6 17:07
 */
public class LeetCode_92_1_反转链表II {
    private boolean stop;
    private ListNode left;

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) {
            return null;
        }

        stop = false;
        ListNode right = left = head;
        reserve(right, m, n);

        return head;
    }

    private void reserve(ListNode right, int m, int n) {
        if (n == 1) {
            return;
        }

        right = right.next;

        if (m > 1) {
            left = left.next;
        }

        reserve(right, m - 1, n - 1);
        if (left == right || right.next == left) {
            stop = true;
        }

        if (!stop) {
            int tmp = left.val;
            left.val = right.val;
            right.val = tmp;

            left = left.next;
        }
    }
}
