package com.bottomlord.week_045;

import com.bottomlord.ListNode;

import java.util.LinkedList;

/**
 * @author ChenYue
 * @date 2020/5/16 15:45
 */
public class LeetCode_25_1_K个一组翻转链表 {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        if (k == 1) {
            return head;
        }

        LinkedList<ListNode> heads = new LinkedList<>(),
                             tails = new LinkedList<>();

        ListNode node = head, pre = null;
        int count = 0;
        while(node != null) {
            count++;
            if (count % k == 1) {
                tails.add(node);
            }

            if (count % k == 0) {
                heads.add(node);
            }

            node = node.next;
        }

        if (heads.size() != tails.size()) {
            heads.add(tails.getLast());
            tails.removeLast();
        }

        node = head;
        while (node != null) {
            if (heads.size() != tails.size()) {
                if (node == heads.getLast()) {
                    while (node != null) {
                        pre = node;
                        node = node.next;
                    }
                    break;
                }
            }

            ListNode next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }

        if (heads.size() == tails.size()) {
            tails.getLast().next = null;
        }

        for (int i = 0; i < tails.size() && i + 1 < heads.size(); i++) {
            tails.get(i).next = heads.get(i + 1);
        }

        return heads.getFirst();
    }
}
