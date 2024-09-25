package com.bottomlord.week_234;

import com.bottomlord.ListNode;

/**
 * @author chen yue
 * @date 2024-01-06 22:10:30
 */
public class LeetCode_2807_1_在链表中插入最大公约数 {
    public ListNode insertGreatestCommonDivisors(ListNode head) {
        ListNode node = head;
        while (node != null && node.next != null) {
            ListNode gcd = new ListNode(gcd(node.val, node.next.val)),
                     next = node.next;
            node.next = gcd;
            gcd.next = next;
            node = next;
        }

        return head;
    }

    private int gcd(int x, int y) {
        int mod = x % y;
        return mod == 0 ? y : gcd(y, mod);
    }
}
