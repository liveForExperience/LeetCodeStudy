package com.bottomlord.week_037;

import com.bottomlord.ListNode;

/**
 * @author ThinkPad
 * @date 2020/3/17 9:07
 */
public class Interview_0201_2 {
    public ListNode removeDuplicateNodes(ListNode head) {
        ListNode node = head;
        while (node != null) {
            ListNode innerNode = node.next, pre = node;
            while (innerNode != null) {
                if (node.val == innerNode.val) {
                    pre.next = innerNode.next;
                } else {
                    pre = innerNode;
                }
                innerNode = innerNode.next;
            }
            node = node.next;
        }
        return head;
    }
}