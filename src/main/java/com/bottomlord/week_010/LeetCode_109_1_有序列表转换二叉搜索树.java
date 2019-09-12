package com.bottomlord.week_010;

import com.bottomlord.ListNode;
import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_109_1_有序列表转换二叉搜索树 {
    public TreeNode sortedListToBST(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            list.add(node.val);
            node = node.next;
        }

        if (list.size() == 0) {
            return null;
        }

        return dfs(list, 0, list.size());
    }

    private TreeNode dfs(List<Integer> list, int left, int right) {
        if (left == right) {
            return null;
        }

        int index = left + (right - left) / 2;
        TreeNode root = new TreeNode(list.get(index));
        root.left = dfs(list, left, index);
        root.right = dfs(list, index + 1, right);
        return root;
    }
}
