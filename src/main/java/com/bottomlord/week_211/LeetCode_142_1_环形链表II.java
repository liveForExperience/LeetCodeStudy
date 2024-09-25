package com.bottomlord.week_211;

import com.bottomlord.ListNode;

/**
 * @author chen yue
 * @date 2023-07-30 12:39:47
 */
public class LeetCode_142_1_环形链表II {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        ListNode meet = null;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                meet = slow;
                break;
            }
        }

        if (meet == null) {
            return null;
        }

        ListNode a = meet, b = head;
        while (a != b) {
            a = a.next;
            b = b.next;
        }

        return a;
    }
}
