package com.bottomlord.week_048;

import com.bottomlord.ListNode;

/**
 * @author ChenYue
 * @date 2020/6/5 8:50
 */
public class LeetCode_19_3 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode(0), node = head;
        dummy.next = head;
        int len = 0;
        while (node != null) {
            node = node.next;
            len++;
        }

        len -= n;
        node = dummy;
        while (len-- > 0) {
            node = node.next;
        }

        node.next = node.next.next;

        return dummy.next;
    }
}
