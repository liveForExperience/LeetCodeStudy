package com.bottomlord.week_7;

import com.bottomlord.ListNode;

import java.util.HashSet;
import java.util.Set;

public class LeetCode_141_1_环形链表 {
    public boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        ListNode node = head;
        while (node != null) {
            if (set.contains(node)) {
                return true;
            }
            set.add(node);
            node = node.next;
        }
        return false;
    }
}
