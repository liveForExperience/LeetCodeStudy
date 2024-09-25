package com.bottomlord.week_012;

import com.bottomlord.ListNode;

public class LeetCode_148_1_排序链表 {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode tmp = slow.next;
        slow.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(tmp);

        ListNode node = new ListNode(0);
        ListNode res = node;

        while (left != null && right != null) {
            if (left.val < right.val) {
                node.next = left;
                left = left.next;
            } else {
                node.next = right;
                right = right.next;
            }

            node = node.next;
        }

        node.next = left == null ? right : left;

        return res.next;
    }
}
