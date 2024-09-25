package com.bottomlord.week_089;

import com.bottomlord.ListNode;

/**
 * @author ChenYue
 * @date 2021/3/25 8:25
 */
public class LeetCode_82_1_删除排序链表中的重复元素II {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode node = head, fake = new ListNode(0), pre = fake;
        fake.next = head;
        while (node != null && node.next != null) {
            ListNode next = node.next;
            while (next != null && node.val == next.val) {
                next = next.next;
            }

            if (node.next != next) {
                pre.next = next;
                node = next;
            } else {
                pre = node;
                node = node.next;
            }
        }
        return fake.next;
    }
}
