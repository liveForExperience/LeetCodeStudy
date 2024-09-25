package com.bottomlord.week_008;

import com.bottomlord.ListNode;

public class LeetCode_234_2 {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        ListNode slow = head, fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode node = slow.next, pre = null, next;
        while (node != null) {
            next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }

        while (pre != null && head != null) {
            if (pre.val != head.val) {
                return false;
            }

            pre = pre.next;
            head = head.next;
        }

        return true;
    }
}