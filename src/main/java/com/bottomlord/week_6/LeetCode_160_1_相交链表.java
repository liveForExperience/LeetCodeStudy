package com.bottomlord.week_6;

import com.bottomlord.ListNode;

public class LeetCode_160_1_相交链表 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int aLen = 0,  bLen = 0;
        ListNode aNode = headA;
        while (aNode != null) {
            aLen++;
            aNode = aNode.next;
        }

        ListNode bNode = headB;
        while (bNode != null) {
            bLen++;
            bNode = bNode.next;
        }

        ListNode longList;
        ListNode shortList;
        int diff;
        if (aLen > bLen) {
            longList = headA;
            shortList = headB;
            diff = aLen - bLen;
        } else {
            longList = headB;
            shortList = headA;
            diff = bLen - aLen;
        }

        while (diff-- > 0) {
            longList = longList.next;
        }

        while (longList != shortList && longList != null && shortList != null) {
            longList = longList.next;
            shortList = shortList.next;
        }

        return longList;
    }
}
