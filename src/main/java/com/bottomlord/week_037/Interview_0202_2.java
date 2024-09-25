package com.bottomlord.week_037;

import com.bottomlord.ListNode;

/**
 * @author ThinkPad
 * @date 2020/3/18 8:42
 */
public class Interview_0202_2 {
    public int kthToLast(ListNode head, int k) {
        ListNode node = head;
        int len = 0;
        while (node != null) {
            len++;
            node = node.next;
        }

        node = head;
        int num = len - k;
        while (num-- > 0) {
            node = node.next;
        }

        return node.val;
    }
}