package com.bottomlord.week_010;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_701_1_二叉搜索树的插入操作 {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        List<Integer> list = new ArrayList<>();
        dfs(list, root);

        List<Integer> newList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (i == 0 && val < list.get(i)) {
                newList.add(val);
            }

            newList.add(list.get(i));

            if (i != list.size() - 1 && val > list.get(i) && val < list.get(i + 1)) {
                newList.add(val);
            }

            if (i == list.size() - 1 && val > list.get(i)) {
                newList.add(val);
            }
        }

        return dfs2(newList, 0, newList.size());
    }

    private void dfs(List<Integer> list, TreeNode node) {
        if (node == null) {
            return;
        }

        dfs(list, node.left);
        list.add(node.val);
        dfs(list, node.right);
    }

    private TreeNode dfs2(List<Integer> list, int head, int tail) {
        if (head == tail) {
            return null;
        }

        int mid = head + (tail - head) / 2;
        TreeNode root = new TreeNode(list.get(mid));
        root.left = dfs2(list, head, mid);
        root.right = dfs2(list, mid + 1, tail);
        return root;
    }
}
