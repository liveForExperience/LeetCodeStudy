package com.bottomlord.week_089;

import com.bottomlord.ListNode;

/**
 * @author ChenYue
 * @date 2021/3/27 11:28
 */
public class LeetCode_61_1_旋转链表 {
    public ListNode rotateRight(ListNode head, int k) {
        ListNode node = head, pre = null;
        int len = 0;
        while (node != null) {
            len++;
            pre = node;
            node = node.next;

            if (node == null) {
                pre.next = head;
            }
        }

        if (pre == null) {
            return null;
        }

        len -= k;
        node = pre.next;
        while (len-- > 0) {
            pre = node;
            node = node.next;
        }

        pre.next = null;

        return node;
    }
}
