package com.bottomlord.week_010;

import com.bottomlord.ListNode;
import com.bottomlord.TreeNode;

public class LeetCode_109_2 {
    public TreeNode sortedListToBST(ListNode head) {
        return head == null ? null : dfs(head, null);
    }

    private TreeNode dfs(ListNode head, ListNode tail) {
        if (head == tail) {
            return null;
        }

        ListNode slow = head, fast = head;
        while (fast != tail && fast.next != tail) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode mid = slow;
        TreeNode root = new TreeNode(mid.val);
        root.left = dfs(head, slow);
        root.right = dfs(slow.next, tail);
        return root;
    }
}