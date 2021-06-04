package com.bottomlord.week_099;

import com.bottomlord.ListNode;

/**
 * @author ChenYue
 * @date 2021/6/4 9:01
 */
public class LeetCode_160_2 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode nodeA = headA, nodeB = headB;
        while (nodeA != nodeB) {
            nodeA = nodeA == null ? headB : nodeA.next;
            nodeB = nodeB == null ? headA : nodeB.next;
        }
        return nodeA;
    }
}
