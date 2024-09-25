package com.bottomlord.week_021;

import com.bottomlord.ListNode;

public class LeetCode_445_1_两数相加II {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l = reserve(l1);
        ListNode r = reserve(l2);

        int carry = 0;
        ListNode cur = null, pre;
        while (l != null || r != null) {
            int lnum = l == null ? 0 : l.val;
            int rnum = r == null ? 0 : r.val;

            int val = lnum + rnum + carry;
            carry = val / 10;

            pre = cur;
            cur = new ListNode(val % 10);
            cur.next = pre;

            l = l == null ? null : l.next;
            r = r == null ? null : r.next;
        }

        if (carry == 1) {
            ListNode head = new ListNode(1);
            head.next = cur;
            return head;
        }

        return cur;
    }

    private ListNode reserve(ListNode node) {
        ListNode pre = null;
        while (node != null) {
            ListNode next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        return pre;
    }
}
