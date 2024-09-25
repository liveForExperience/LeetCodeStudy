package com.bottomlord.week_213;

import com.bottomlord.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author chen yue
 * @date 2023-08-12 16:57:18
 */
public class LeetCode_23_1_合并K个升序链表 {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode fake = new ListNode(0), cur = fake;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(x -> x.val));
        for (ListNode node : lists) {
            queue.offer(node);
        }
        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            cur.next = node;
            cur = cur.next;
            if (node.next != null) {
                queue.offer(node.next);
                node.next = null;
            }
        }

        return fake.next;
    }
}
