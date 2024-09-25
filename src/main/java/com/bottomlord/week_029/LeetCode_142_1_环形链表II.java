package com.bottomlord.week_029;

import com.bottomlord.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ThinkPad
 * @date 2020/1/21 15:40
 */
public class LeetCode_142_1_环形链表II {
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return head;
            }
            set.add(head);
            head = head.next;
        }
        return null;
    }
}