package com.bottomlord.week_037;

import com.bottomlord.ListNode;

/**
 * @author ThinkPad
 * @date 2020/3/19 9:07
 */
public class Interview_0206_2 {
    public boolean isPalindrome(ListNode head) {
        ListNode node = head, pre = null;
        while (node != null) {
            ListNode cur = new ListNode(node.val);
            cur.next = pre;
            pre = cur;
            node = node.next;
        }

        while (pre != null && head != null) {
            if (pre.val != head.val) {
                return false;
            }
            pre = pre.next;
            head = head.next;
        }

        return pre == null && head == null;
    }
}
