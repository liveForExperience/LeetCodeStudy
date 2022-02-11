package com.bottomlord.week_135;

import com.bottomlord.ListNode;

/**
 * @author chen yue
 * @date 2022-02-11 09:39:22
 */
public class LeetCode_offerII24_1_反转链表 {
    public ListNode reverseList(ListNode head) {
        ListNode node = head, pre = null;
        while (node != null) {
            ListNode next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        return pre;
    }
}
