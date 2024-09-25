package com.bottomlord.week_042;

import com.bottomlord.ListNode;

/**
 * @author ChenYue
 * @date 2020/4/26 8:17
 */
public class LeetCode_26_2 {
    public ListNode mergeKLists(ListNode[] lists) {
        int len = lists.length;
        if (len == 0) {
            return null;
        }

        while (len > 1) {
            int i = 0;

            for (i = 0; i < len / 2; i++) {
                lists[i] = merge(lists[2 * i], lists[2 * i + 1]);
            }

            if ((len & 1) == 1) {
                lists[i] = lists[len - 1];
                len++;
            }

            len /= 2;
        }

        return lists[0];
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode start = new ListNode(0), pre = start;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                pre.next = l2;
                l2 = l2.next;
            } else if (l2 == null) {
                pre.next = l1;
                l1 = l1.next;
            } else if (l1.val < l2.val) {
                pre.next = l1;
                l1 = l1.next;
            } else {
                pre.next = l2;
                l2 = l2.next;
            }

            pre = pre.next;
        }

        return start.next;
    }
}