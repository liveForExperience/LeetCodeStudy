package com.bottomlord.week_048;

import com.bottomlord.ListNode;

/**
 * @author ChenYue
 * @date 2020/6/5 8:25
 */
public class LeetCode_19_1_删除链表的倒数第N个节点 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int len = 0;
        ListNode node = head, pre = null;
        while (node != null) {
            len++;
            node = node.next;
        }

        int index = len - n;
        node = head;
        while (index-- > 0) {
            pre = node;
            node = node.next;
        }

        if (pre != null) {
            pre.next = node.next;
        } else {
            head = head.next;
        }

        return head;
    }
}
