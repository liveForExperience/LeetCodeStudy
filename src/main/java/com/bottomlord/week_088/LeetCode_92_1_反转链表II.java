package com.bottomlord.week_088;

import com.bottomlord.ListNode;

/**
 * @author ChenYue
 * @date 2021/3/18 8:21
 */
public class LeetCode_92_1_反转链表II {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode node = head, fakeHead = new ListNode(0), pre = fakeHead, beforeStart;
        fakeHead.next = head;
        int index = 1;
        while (node != null) {
            if (index == left) {
                beforeStart = pre;
                ListNode start = node, afterEnd = null;
                while (index <= right) {
                    ListNode next = node.next;
                    node.next = pre;
                    pre = node;
                    if (index == right) {
                        afterEnd = next;
                    } else {
                        node = next;
                    }

                    index++;
                }


                beforeStart.next = node;
                start.next = afterEnd;

                node = afterEnd;
            } else {
                pre = node;
                node = node.next;
                index++;
            }
        }

        return fakeHead.next;
    }
}
