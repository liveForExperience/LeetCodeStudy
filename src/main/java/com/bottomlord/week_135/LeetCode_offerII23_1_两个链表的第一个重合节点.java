package com.bottomlord.week_135;

import com.bottomlord.ListNode;

/**
 * @author chen yue
 * @date 2022-02-11 09:16:25
 */
public class LeetCode_offerII23_1_两个链表的第一个重合节点 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode nodeA = headA, nodeB = headB;
        if (nodeA == nodeB) {
            return nodeA;
        }

        int a = count(nodeA), b = count(nodeB), index = 0;
        while (index++ < a + b) {
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

            if (nodeA == nodeB) {
                return nodeA;
            }
        }

        return null;
    }

    private int count(ListNode node) {
        int count = 0;
        while (node != null) {
            count++;
            node = node.next;
        }
        return count;
    }
}
