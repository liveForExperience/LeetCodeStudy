package com.bottomlord.week_105;

import com.bottomlord.ListNode;

/**
 * @author ChenYue
 * @date 2021/7/12 8:25
 */
public class LeetCode_1290_1_二进制链表转整数 {
    public int getDecimalValue(ListNode head) {
        int ans = 0;
        ListNode node = head;
        while (node != null) {
            ans = ans * 2 + node.val;
            node = node.next;
        }
        return ans;
    }
}
