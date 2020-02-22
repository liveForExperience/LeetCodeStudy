package com.bottomlord.week_033;

import com.bottomlord.ListNode;

/**
 * @author ThinkPad
 * @date 2020/2/22 12:37
 */
public class Interview_25_2 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0), cur = head;

        while (l1 != null || l2 != null) {
            if (l1 == null) {
                cur.next = l2;
                break;
            }

            if (l2 == null) {
                cur.next = l1;
                break;
            }

            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        return head.next;
    }
}