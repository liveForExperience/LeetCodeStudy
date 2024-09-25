package com.bottomlord.week_027;

import com.bottomlord.ListNode;

/**
 * @author ThinkPad
 * @date 2020/1/8 21:20
 */
public class LeetCode_92_2 {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) {
            return null;
        }

        ListNode pre = null, cur = head;
        while (m > 1) {
            pre = cur;
            cur = cur.next;
            m--;
            n--;
        }

        ListNode con = pre, tail = cur, tmp = null;
        while (n > 0) {
            tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
            n--;
        }

        if (con != null) {
            con.next = pre;
        } else {
            head = pre;
        }

        tail.next = cur;
        return head;
    }
}