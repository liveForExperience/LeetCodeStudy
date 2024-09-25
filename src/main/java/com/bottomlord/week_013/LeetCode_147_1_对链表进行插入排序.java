package com.bottomlord.week_013;

import com.bottomlord.ListNode;

public class LeetCode_147_1_对链表进行插入排序 {
    public ListNode insertionSortList(ListNode head) {
        ListNode pre = new ListNode(0), index;
        pre.next = head;

        while (head != null && head.next != null) {
            if (head.next.val >= head.val) {
                head = head.next;
                continue;
            }

            index = pre;
            while (index.next.val < head.next.val) {
                index = index.next;
            }

            ListNode cur = head.next;
            head.next = cur.next;
            cur.next = index.next;
            index.next = cur;
        }

        return pre.next;
    }
}
