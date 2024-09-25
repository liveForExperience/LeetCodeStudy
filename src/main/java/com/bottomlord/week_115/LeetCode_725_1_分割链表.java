package com.bottomlord.week_115;

import com.bottomlord.ListNode;

/**
 * @author chen yue
 * @date 2021-09-22 08:33:05
 */
public class LeetCode_725_1_分割链表 {
    public ListNode[] splitListToParts(ListNode head, int k) {
        int n = 0, len = 0, left = 0;
        ListNode node = head;
        while (node != null) {
            n++;
            node = node.next;
        }

        len = n / k;
        left = n % k;

        node = head;
        ListNode[] ans = new ListNode[k];

        for (int i = 0; i < k; i++) {
            if (node == null) {
                ans[i] = null;
                continue;
            }

            ListNode pre = null;
            ans[i] = node;
            int curLen = len + (left-- > 0 ? 1 : 0);
            for (int j = 0; j < curLen; j++) {
                if (node == null) {
                    break;
                }
                pre = node;
                node = node.next;
            }

            if (pre != null) {
                pre.next = null;
            }
        }

        return ans;
    }
}
