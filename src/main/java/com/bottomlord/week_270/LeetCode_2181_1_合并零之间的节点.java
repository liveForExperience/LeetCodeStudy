package com.bottomlord.week_270;

import com.bottomlord.ListNode;

/**
 * @author chen yue
 * @date 2024-09-09 22:41:32
 */
public class LeetCode_2181_1_合并零之间的节点 {
    public ListNode mergeNodes(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode start = head, node = head;
        while (node != null) {
            node = node.next;
            int sum = 0;
            while (node != null && node.val != 0) {
                sum += node.val;
                node = node.next;
            }

            start.val = sum;
            start.next = node;
            if (node != null && node.next == null) {
                start.next = null;
                break;
            }
            start = node;
        }

        return head;
    }
}
