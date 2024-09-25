
package com.bottomlord.week_030;

import com.bottomlord.ListNode;

/**
 * @author ThinkPad
 * @date 2020/1/31 20:03
 */
public class LeetCode_82_1_删除排序链表中的重复元素II {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode start = new ListNode(0), pre, cur = start;
        start.next = head;
        while (cur != null) {
            pre = cur;
            cur = cur.next;
            while (cur != null && cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
                int val = cur.val;
                while (cur != null && cur.val == val) {
                    cur = cur.next;
                }
            }
            pre.next = cur;
        }
        return start.next;
    }
}
