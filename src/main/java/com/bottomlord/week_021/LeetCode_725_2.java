package com.bottomlord.week_021;

import com.bottomlord.ListNode;

public class LeetCode_725_2 {
    public ListNode[] splitListToParts(ListNode root, int k) {
        int count = 0;
        ListNode node = root;
        while (node != null) {
            count++;
            node = node.next;
        }

        int len = count / k, left = count % k;
        ListNode[] ans = new ListNode[k];

        node = root;
        for (int i = 0; i < k; i++) {
            ListNode head = node;
            for (int j = 0; j < len + (i < left ? 0 : -1); j++) {
                if (node != null) {
                    node = node.next;
                }
            }

            if (node != null) {
                ListNode pre = node;
                node = node.next;
                pre.next = null;
            }

            ans[i] = head;
        }

        return ans;
    }
}