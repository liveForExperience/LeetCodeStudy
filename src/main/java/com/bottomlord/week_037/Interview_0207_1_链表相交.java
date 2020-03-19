package com.bottomlord.week_037;

import com.bottomlord.ListNode;

/**
 * @author ThinkPad
 * @date 2020/3/19 13:07
 */
public class Interview_0207_1_链表相交 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode nodeA = headA, nodeB = headB;
        while (nodeA != nodeB) {
            if (nodeA == null) {
                nodeA = headB;
            } else {
                nodeA = nodeA.next;
            }

            if (nodeB == null) {
                nodeB = headA;
            } else {
                nodeB = nodeB.next;
            }
        }

        return nodeA;
    }
}
