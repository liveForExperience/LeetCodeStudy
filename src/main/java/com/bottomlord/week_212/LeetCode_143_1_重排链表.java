package com.bottomlord.week_212;

import com.bottomlord.ListNode;

/**
 * @author chen yue
 * @date 2023-07-31 09:26:58
 */
public class LeetCode_143_1_重排链表 {
    public void reorderList(ListNode head) {
        ListNode[] nodes = new ListNode[1001];
        int cnt = 0;
        ListNode node = head;
        while (node != null) {
            nodes[cnt++] = node;
            node = node.next;
        }

        int start = 0, end = cnt - 1;
        ListNode fake = new ListNode(0), pre = fake;
        while (start <= end) {
            pre.next = nodes[start];

            if (start != end) {
                nodes[start].next = nodes[end];
            }

            pre = nodes[end];

            start++;
            end--;
        }

        if (cnt % 2 == 0) {
            nodes[start].next = null;
        } else {
            nodes[end].next = null;
        }
    }
}
