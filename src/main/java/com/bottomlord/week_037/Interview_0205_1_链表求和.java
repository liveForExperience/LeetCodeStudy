package com.bottomlord.week_037;

import com.bottomlord.ListNode;

/**
 * @author ThinkPad
 * @date 2020/3/19 8:37
 */
public class Interview_0205_1_链表求和 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0), pre = head;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int sum = 0;
            if (l1 == null) {
                sum += l2.val + carry;
                l2 = l2.next;
            } else if (l2 == null) {
                sum += l1.val + carry;
                l1 = l1.next;
            } else {
                sum += l1.val + l2.val + carry;
                l1 = l1.next;
                l2 = l2.next;
            }

            carry = sum >= 10 ? 1 : 0;
            sum %= 10;

            ListNode cur = new ListNode(sum);
            pre.next = cur;
            pre = cur;
        }

        if (carry == 1) {
            pre.next = new ListNode(1);
        }

        return head.next;
    }
}
