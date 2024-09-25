package com.bottomlord.week_033;

import com.bottomlord.ListNode;

/**
 * @author ThinkPad
 * @date 2020/2/19 12:36
 */
public class Interview_18_1_删除链表的节点 {
    public ListNode deleteNode(ListNode head, int val) {
        ListNode node = head, fakeHead = new ListNode(0), pre = fakeHead;
        fakeHead.next = node;
        while (node != null) {
            if (node.val == val) {
                if (pre == fakeHead) {
                    fakeHead.next = node.next;
                } else {
                    pre.next = node.next;
                }
                break;
            }

            pre = node;
            node = node.next;
        }

        return fakeHead.next;
    }
}
