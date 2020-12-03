package com.bottomlord.week_073;

import com.bottomlord.ListNode;

/**
 * @author ChenYue
 * @date 2020/12/2 19:02
 */
public class LeetCode_369_1_给单链表加一 {
    public ListNode plusOne(ListNode head) {
        if (head == null) {
            return null;
        }

        int carry = recurse(head);
        if (carry == 1) {
            ListNode node = new ListNode(1);
            node.next = head;
            return node;
        }

        return head;
    }

    private int recurse(ListNode node) {
        if (node.next == null) {
            int num = node.val + 1;
            if (num > 9) {
                node.val = 0;
                return 1;
            } else {
                node.val = num;
                return 0;
            }
        }

        int carry = recurse(node.next);
        int num = carry + node.val;
        if (num > 9) {
            node.val = 0;
            return 1;
        }

        node.val = num;
        return 0;
    }
}
