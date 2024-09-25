package com.bottomlord.week_212;

import com.bottomlord.ListNode;

/**
 * @author chen yue
 * @date 2023-08-06 18:53:44
 */
public class LeetCode_24_1_两两交换链表中的节点 {
    private boolean flag;
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        flag = true;
        ListNode ans = head.next;
        recursion(head, null, null);
        return ans;
    }

    private void recursion(ListNode cur, ListNode pre1, ListNode pre2) {
        if (cur == null) {
            return;
        }

        flag = !flag;

        if (!flag) {
            recursion(cur.next, cur, pre1);
        } else {
            ListNode next = cur.next;
            cur.next = pre1;
            pre1.next = next;
            if (pre2 != null) {
                pre2.next = cur;
            }
            recursion(next, pre1, cur);
        }
    }
}
