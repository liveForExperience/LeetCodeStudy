package com.bottomlord.week_048;

import com.bottomlord.ListNode;

/**
 * @author ChenYue
 * @date 2020/6/5 8:43
 */
public class LeetCode_19_2 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }

        ListNode node = head;
        int len = 0;
        while (node != null) {
            len++;
            node = node.next;
        }

        int index = len - n;
        if (index == 0) {
            return head.next;
        }

        node = head;
        while (--index > 0) {
            node = node.next;
        }

        node.next = node.next.next;

        return head;
    }
}
