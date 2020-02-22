package com.bottomlord.week_033;

import com.bottomlord.ListNode;

/**
 * @author ThinkPad
 * @date 2020/2/22 11:54
 */
public class Interview_24_1_反转链表 {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null, node = head;
        while (node != null) {
            ListNode next = node.next;
            node.next = pre;
            pre = node;
            if (next != null) {
                node = next;
            }
        }

        return node;
    }
}
