package com.bottomlord.week_3;

import com.bottomlord.ListNode;

/**
 * @author LiveForExperience
 * @date 2019/7/24 11:13
 */
public class LeetCode_876_2 {
    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }
}