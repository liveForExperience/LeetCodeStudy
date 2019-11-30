package com.bottomlord.week_021;

import com.bottomlord.ListNode;

public class LeetCode_725_1_分隔链表 {
    public ListNode[] splitListToParts(ListNode root, int k) {
        int count = 0;
        ListNode cur = root;
        while (cur != null) {
            count++;
            cur = cur.next;
        }

        int len = count / k, left = count % k;

        ListNode[] ans = new ListNode[k];

        cur = root;
        for (int i = 0; i < k; i++) {
            ListNode head = new ListNode(0), write = head;
            for (int j = 0; j < len + (i < left ? 1 : 0); j++) {
                write = write.next = new ListNode(cur.val);
                cur = cur.next;
            }
            ans[i] = head.next;
        }

        return ans;
    }
}
