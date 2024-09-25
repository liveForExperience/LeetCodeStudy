package com.bottomlord.week_112;

import com.bottomlord.ListNode;

/**
 * @author chen yue
 * @date 2021-09-02 08:23:48
 */
public class LeetCode_offer22_1_链表中倒数第K个节点 {
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode node = head;
        int n = 0;
        while (node != null) {
            n++;
            node = node.next;
        }

        int index = n - k;
        node = head;
        while (index-- > 0) {
            node = node.next;
        }

        return node;
    }
}
