package com.bottomlord.week_110;

import com.bottomlord.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2021-08-16 08:55:35
 */
public class LeetCode_1474_1_删除链表M个节点后的N个节点 {
    public ListNode deleteNodes(ListNode head, int m, int n) {
        List<Integer> list = new ArrayList<>();
        ListNode node = head;
        int index = 0;
        while (node != null) {
            if (index < m) {
                list.add(node.val);
            }

            if (index == m + n) {
                index = 0;
            } else {
                index++;
            }

            node = node.next;
        }

        ListNode fake = new ListNode(0),
                 pre = fake;
        for (int num : list) {
            pre.next = new ListNode(num);
            pre = pre.next;
        }

        return fake.next;
    }
}
