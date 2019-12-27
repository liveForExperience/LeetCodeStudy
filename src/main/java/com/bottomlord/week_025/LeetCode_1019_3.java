package com.bottomlord.week_025;

import com.bottomlord.ListNode;

/**
 * @author ThinkPad
 * @date 2019/12/27 21:37
 */
public class LeetCode_1019_3 {
    public int[] nextLargerNodes(ListNode head) {
        int len = 0;
        ListNode node = head;
        while (node != null) {
            len++;
            node = node.next;
        }

        int[] stack = new int[2 * len], ans = new int[len];
        int index = 0, i = 0;
        node = head;
        while (node != null) {
            while (index != 0 && stack[index - 2] < node.val) {
                ans[stack[--index]] = node.val;
                index--;
            }

            stack[index++] = node.val;
            stack[index++] = i;

            i++;
            node = node.next;
        }

        return ans;
    }
}