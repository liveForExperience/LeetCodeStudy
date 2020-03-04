package com.bottomlord.week_035;

import com.bottomlord.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ThinkPad
 * @date 2020/3/4 8:21
 */
public class Interview_52_1_两个链表的第一个公共节点 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        ListNode node = headA;
        while (node != null) {
            set.add(node);
            node = node.next;
        }

        node = headB;
        while (node != null) {
            if (set.contains(node)) {
                return node;
            }
            node = node.next;
        }

        return null;
    }
}
