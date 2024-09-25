package com.bottomlord.week_003;

import com.bottomlord.ListNode;

/**
 * @author LiveForExperience
 * @date 2019/7/22 12:35
 */
public class LeetCode_206_1_反转链表 {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }
}
