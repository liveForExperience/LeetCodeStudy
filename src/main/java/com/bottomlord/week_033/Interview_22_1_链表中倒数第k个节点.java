package com.bottomlord.week_033;

import com.bottomlord.ListNode;

/**
 * @author ThinkPad
 * @date 2020/2/21 17:50
 */
public class Interview_22_1_链表中倒数第k个节点 {
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode node = head;
        int count = 0;
        while (node != null) {
            node = node.next;
            count++;
        }

        node = head;
        for (int i = 0; i <= count - k; i++) {
            node = node.next;
        }

        return node;
    }
}
