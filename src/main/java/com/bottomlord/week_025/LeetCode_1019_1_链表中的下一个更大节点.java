package com.bottomlord.week_025;

import com.bottomlord.ListNode;

/**
 * @author ThinkPad
 * @date 2019/12/26 20:19
 */
public class LeetCode_1019_1_链表中的下一个更大节点 {
    public int[] nextLargerNodes(ListNode head) {
        int len = 0, index = 0;
        ListNode node = head;
        while (node != null) {
            len++;
            node = node.next;
        }

        int[] ans = new int[len];
        node = head;
        while (node != null) {
            int val = node.val, larger = 0;
            ListNode innerNode = node.next;
            while (innerNode != null) {
                if (innerNode.val > val) {
                    larger = innerNode.val;
                    break;
                }
                innerNode = innerNode.next;
            }

            ans[index++] = larger;
            node = node.next;
        }

        return ans;
    }
}
