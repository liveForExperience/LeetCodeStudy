package com.bottomlord.week_135;

import com.bottomlord.ListNode;

/**
 * @author chen yue
 * @date 2022-02-11 09:34:47
 */
public class LeetCode_offerII23_2 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode nodeA = headA, nodeB = headB;
        while (nodeA != nodeB) {
            nodeA = nodeA == null ? headB : nodeA.next;
            nodeB = nodeB == null ? headA : nodeB.next;
        }

        return nodeA;
    }
}