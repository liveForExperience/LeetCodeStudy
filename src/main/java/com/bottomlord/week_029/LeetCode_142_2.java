package com.bottomlord.week_029;

import com.bottomlord.ListNode;

/**
 * @author ThinkPad
 * @date 2020/1/21 16:07
 */
public class LeetCode_142_2 {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head, meet = null;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (fast == slow) {
                meet = fast;
                break;
            }
        }

        if (meet == null) {
            return null;
        }

        while (head != meet) {
            head = head.next;
            meet = meet.next;
        }

        return meet;
    }
}