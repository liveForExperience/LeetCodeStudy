package com.bottomlord.week_110;

import com.bottomlord.ListNode;

/**
 * @author chen yue
 * @date 2021-08-16 23:05:21
 */
public class LeetCode_1474_2 {
    public ListNode deleteNodes(ListNode head, int m, int n) {
        int index = 0;
        ListNode fake = new ListNode(0), pre = fake, node = head;
        fake.next = head;
        while (node != null) {
            if (index < m) {
                pre = node;
                node = node.next;
            } else if (index < m + n) {
                node = node.next;
                pre.next = node;
            }

            index++;

            if (index == m + n) {
                index = 0;
            }
        }

        return fake.next;
    }
}
