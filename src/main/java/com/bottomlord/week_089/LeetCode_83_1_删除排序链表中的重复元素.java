package com.bottomlord.week_089;

import com.bottomlord.ListNode;

/**
 * @author ChenYue
 * @date 2021/3/26 8:21
 */
public class LeetCode_83_1_删除排序链表中的重复元素 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode node = head;
        while (node != null && node.next != null) {
            ListNode next = node.next;
            while (next != null && node.val == next.val) {
                next = next.next;
            }

            node.next = next;
            node = next;
        }
        return head;
    }
}
