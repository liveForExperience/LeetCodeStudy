package com.bottomlord.week_099;

import com.bottomlord.ListNode;

/**
 * @author ChenYue
 * @date 2021/6/4 8:55
 */
public class LeetCode_160_1_相交链表 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode nodeA = headA, nodeB = headB;
        while (nodeA != nodeB) {
            nodeA = nodeA == null ? headA : nodeA.next;
            nodeB = nodeB == null ? headB : nodeB.next;
        }

        return nodeA;
    }
}
