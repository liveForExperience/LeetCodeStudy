package com.bottomlord.week_042;

import com.bottomlord.ListNode;

/**
 * @author ChenYue
 * @date 2020/4/22 12:53
 */
public class LeetCode_2_1_两数相加 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode node1 = l1, node2 = l2, pre = new ListNode(0), start = pre;
        int carry = 0;
        while (node1 != null || node2 != null) {
            int num;
            if (node1 == null) {
                num = carry + node2.val;
                pre.next = new ListNode(num % 10);
                node2 = node2.next;
            } else if (node2 == null) {
                num = carry + node1.val;
                pre.next = new ListNode(num % 10);
                node1 = node1.next;
            } else {
                num = node1.val + node2.val + carry;
                pre.next = new ListNode(num % 10);
                node1 = node1.next;
                node2 = node2.next;
            }

            carry = num / 10;
            pre = pre.next;
        }

        if (carry != 0) {
            pre.next = new ListNode(carry);
        }

        return start.next;
    }
}
