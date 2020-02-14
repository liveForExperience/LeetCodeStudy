package com.bottomlord.week_032;

import com.bottomlord.ListNode;

/**
 * @author ThinkPad
 * @date 2020/2/14 13:31
 */
public class Interview_06_2 {
    public int[] reversePrint(ListNode head) {
        ListNode node = head, pre = null, start = node;
        int count = 0;

        while (node != null) {
            ListNode next = node.next;
            node.next = pre;
            pre = node;
            if (next != null) {
                start = node;
            }
            node = next;
            count++;
        }

        int[] ans = new int[count];
        int index = 0;
        while (start != null) {
            ans[index++] = start.val;
            start = start.next;
        }
        return ans;
    }
}