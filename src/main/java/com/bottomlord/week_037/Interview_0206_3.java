package com.bottomlord.week_037;

import com.bottomlord.ListNode;

/**
 * @author ThinkPad
 * @date 2020/3/19 12:54
 */
public class Interview_0206_3 {
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        if (fast != null) {
            slow = slow.next;
        }

        ListNode right = slow == null ? head : slow;
        while (slow != null) {
            if (slow == right) {
                slow = slow.next;
                right.next = null;
                continue;
            }
            ListNode next = slow.next;
            slow.next = right;
            right = slow;
            slow = next;
        }

        ListNode left = head;
        while (left != null && right != null) {
            if (left.val != right.val) {
                return false;
            }
            left = left.next;
            right = right.next;
        }

        return true;
    }
}