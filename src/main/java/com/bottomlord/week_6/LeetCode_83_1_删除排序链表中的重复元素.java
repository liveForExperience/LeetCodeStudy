package com.bottomlord.week_6;

import com.bottomlord.ListNode;

import java.util.HashSet;
import java.util.Set;

public class LeetCode_83_1_删除排序链表中的重复元素 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }

        Set<Integer> set = new HashSet<>();
        set.add(head.val);

        ListNode node = head.next;
        ListNode pre = head;
        while (node != null) {
            if (set.contains(node.val)) {
                pre.next = node.next;
            } else {
                set.add(node.val);
                pre = node;
            }
            node = node.next;
        }

        return head;
    }
}
