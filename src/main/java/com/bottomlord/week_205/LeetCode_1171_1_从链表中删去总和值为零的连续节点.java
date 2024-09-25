package com.bottomlord.week_205;

import com.bottomlord.ListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2023-06-12 19:01:46
 */
public class LeetCode_1171_1_从链表中删去总和值为零的连续节点 {
    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        Map<Integer, ListNode> map = new HashMap<>();
        int sum = 0;
        ListNode node = dummy;
        while (node != null) {
            sum += node.val;
            map.put(sum, node);
            node = node.next;
        }

        sum = 0;
        node = dummy;
        while (node != null) {
            sum += node.val;

            if (map.containsKey(sum)) {
                node.next = map.get(sum).next;
            }

            node = node.next;
        }

        return dummy.next;
    }
}
