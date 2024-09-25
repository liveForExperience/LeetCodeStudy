package com.bottomlord.week_003;

import com.bottomlord.ListNode;

/**
 * @author LiveForExperience
 * @date 2019/7/22 13:17
 */
public class LeetCode_206_2 {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode end = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return end;
    }
}