package com.bottomlord.week_125;

import com.bottomlord.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author chen yue
 * @date 2021-11-29 20:17:30
 */
public class LeetCode_23_1_合并K个升序链表 {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        ListNode root = new ListNode(0);
        ListNode node = root;

        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(x -> x.val));

        for (ListNode list : lists) {
            if (list == null) {
                continue;
            }

            queue.offer(list);
        }

        if (queue.isEmpty()) {
            return null;
        }

        while (!queue.isEmpty()) {
            ListNode cur = queue.poll();
            if (node.next != null) {
                queue.offer(node.next);
            }

            node.next = cur;
            node = cur;
        }

        return root.next;
    }
}
