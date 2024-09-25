package com.bottomlord.week_051;

import com.bottomlord.ListNode;

/**
 * @author ChenYue
 * @date 2020/6/22 17:48
 */
public class LeetCode_61_1_旋转链表 {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        ListNode node = head, pre = null;
        int len = -0;
        while (node != null) {
            pre = node;
            node = node.next;
            len++;
        }

        pre.next = head;
        node = head;
        k = (len - k % len);
        while (k-- > 0) {
            pre = node;
            node = node.next;
        }

        head = node;
        pre.next = null;

        return head;
    }
}
