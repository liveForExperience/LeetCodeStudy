package com.bottomlord.week_032;

import com.bottomlord.ListNode;

/**
 * @author ThinkPad
 * @date 2020/2/14 14:28
 */
public class Interview_06_3 {
    public int[] reversePrint(ListNode head) {
        int count = 0;
        ListNode node = head;

        while (node != null) {
            node = node.next;
            count++;
        }

        node = head;
        int[] ans = new int[count];
        for (int i = count - 1; i >= 0; i--) {
            ans[i] = node.val;
            node = node.next;
        }

        return ans;
    }
}