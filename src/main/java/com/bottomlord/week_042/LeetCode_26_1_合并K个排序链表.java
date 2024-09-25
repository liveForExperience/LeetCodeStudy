package com.bottomlord.week_042;

import com.bottomlord.ListNode;

import java.util.PriorityQueue;

/**
 * @author ChenYue
 * @date 2020/4/26 8:04
 */
public class LeetCode_26_1_合并K个排序链表 {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (ListNode list : lists) {
            ListNode node = list;
            while (node != null) {
                queue.offer(node.val);
                node = node.next;
            }
        }

        ListNode start = new ListNode(0), pre = start;
        while (!queue.isEmpty()) {
            pre.next = new ListNode(queue.poll());
            pre = pre.next;
        }

        return start.next;
    }
}
