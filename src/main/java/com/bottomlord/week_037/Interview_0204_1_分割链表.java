package com.bottomlord.week_037;

import com.bottomlord.ListNode;

/**
 * @author ThinkPad
 * @date 2020/3/18 8:55
 */
public class Interview_0204_1_分割链表 {
    public ListNode partition(ListNode head, int x) {
        ListNode fakeLeftHead = new ListNode(0), fakeRightHead = new ListNode(0), left = null, right  = null, node = head;

        while (node != null) {
            if (node.val < x) {
                if (left == null) {
                    left = node;
                    fakeLeftHead.next = left;
                } else {
                    left.next = node;
                    left = left.next;
                }
            } else {
                if (right == null) {
                    right = node;
                    fakeRightHead.next = right;
                } else {
                    right.next = node;
                    right = right.next;
                }
            }
            node = node.next;
        }

        if (right != null) {
            right.next = null;
        }

        if (left == null) {
            return fakeRightHead.next;
        }

        left.next = fakeRightHead.next;
        return fakeLeftHead.next;
    }
}
